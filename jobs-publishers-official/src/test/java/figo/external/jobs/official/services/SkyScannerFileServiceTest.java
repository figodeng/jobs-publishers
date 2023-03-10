package figo.external.jobs.official.services;

import figo.external.jobs.official.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SkyScannerFileServiceTest extends BaseTest {
    @Autowired
    private SkyScannerFileService scannerFileService;

    @Test
    public void test_getJobs() {
        var jobs = scannerFileService.getJobs();

        Assertions.assertNotNull(jobs);
    }
}
