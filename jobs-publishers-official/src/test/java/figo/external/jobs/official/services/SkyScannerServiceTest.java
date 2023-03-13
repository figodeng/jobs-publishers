package figo.external.jobs.official.services;

import figo.external.jobs.official.BaseTest;
import figo.external.jobs.core.services.PublishService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SkyScannerServiceTest extends BaseTest {
    @Autowired
    private SkyScannerServiceAdapter scannerService;

    @Test
    public void test_getJobs() {
        List jobs = scannerService.getJobs();

        Assertions.assertNotNull(jobs);
    }
}
