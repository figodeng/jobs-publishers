package figo.extenal.jobs.official.services;

import figo.extenal.jobs.official.BaseTest;
import figo.external.jobs.core.services.PublishService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class SkyScannerServiceTest extends BaseTest {
    @Autowired
    private PublishService scannerService;

    @Test
    public void test_getJobs() {
        List jobs = scannerService.getJobs();

        Assertions.assertNotNull(jobs);
    }
}
