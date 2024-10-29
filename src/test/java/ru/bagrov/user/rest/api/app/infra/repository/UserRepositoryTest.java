package ru.bagrov.user.rest.api.app.infra.repository;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.bagrov.user.rest.api.app.infra.repository.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private static final int USERS_BATCH_SIZE = 100;
    private static final int TOTAL_USERS_COUNT = 100_000;

    private static final int NUM_OF_THREADS = 100;
    private static final int NUM_OF_RECORDS = 1_000_000;
    private static final int NUM_OF_RECORDS_PER_THREAD = NUM_OF_RECORDS / NUM_OF_THREADS;

    @Test
    void test_saveUsers() {
        List<UserEntity> users = new ArrayList<>(USERS_BATCH_SIZE);
        for (int i = 0; i < TOTAL_USERS_COUNT; i++) {
            UserEntity user = new UserEntity();
            user.setUserName("UserName" + i);
            user.setFirstName("FirstName" + i);
            user.setSecondName("SecondName" + i);
            user.setAge(50);
            user.setGender("F");
            users.add(user);
            if ((i + 1) % USERS_BATCH_SIZE == 0) {
                userRepository.saveAll(users);
                users.clear();
            }
        }

        if (!users.isEmpty()) {
            userRepository.saveAll(users);
        }

        Optional<UserEntity> userEntityOptional = userRepository.findById(100000L);
        assertNotNull(userEntityOptional.orElse(null));
        assertEquals("UserName99999", userEntityOptional.get().getUserName());
    }

    @Test
    @SneakyThrows
    void testPerformanceWithMultipleConnections() {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);

        List<Future<Long>> futureResults = new ArrayList<>();
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            Future<Long> future = executorService.submit(this::fetchRandomUserAndMeasureTime);
            futureResults.add(future);
        }

        List<Long> executionTimes = new ArrayList<>();
        for (Future<Long> future : futureResults) {
            executionTimes.add(future.get());
        }

        executorService.shutdown();
        boolean timeout = executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        if (timeout) {
            executorService.shutdownNow();
        }

        double maxTime = executionTimes.stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(0);
        long medianTime = calculatePercentile(executionTimes, 50);
        long percentile95Time = calculatePercentile(executionTimes, 95);
        long percentile99Time = calculatePercentile(executionTimes, 99);

        System.out.printf("Общее время выполнения: %d ms\n", (long) maxTime);
        System.out.printf("Медианное время выполнения: %d ms\n", medianTime);
        System.out.printf("95-й процентиль времени выполнения: %d ms\n", percentile95Time);
        System.out.printf("99-й процентиль времени выполнения: %d ms\n", percentile99Time);
    }

    private Long fetchRandomUserAndMeasureTime() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < NUM_OF_RECORDS_PER_THREAD; i++) {
            long randomId = (long) (Math.random() * NUM_OF_RECORDS) + 1;
            userRepository.findById(randomId);
        }
        return System.currentTimeMillis() - startTime;
    }

    private long calculatePercentile(List<Long> executionTimes, double percentile) {
        List<Long> sortedTimes = new ArrayList<>(executionTimes);
        Collections.sort(sortedTimes);

        int index = (int) Math.ceil((percentile / 100.0) * sortedTimes.size()) - 1;
        return sortedTimes.get(index);
    }
}