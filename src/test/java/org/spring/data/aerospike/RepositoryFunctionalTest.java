package org.spring.data.aerospike;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = SpringDataAerospikeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class RepositoryFunctionalTest {

    @Test
    void shouldStoreCurrenciesWithOptimisticLockingConcurrently() {
        //fail on spring boot startup
    }
}
