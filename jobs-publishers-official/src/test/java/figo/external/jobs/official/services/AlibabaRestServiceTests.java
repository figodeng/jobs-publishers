package figo.external.jobs.official.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class AlibabaRestServiceTests {
    private final static AlibabaRestService restService = new AlibabaRestService();

    @Test
    public void test_getCookies() {
        HashMap cookies = restService.getCookies();
        Assertions.assertNotNull(cookies);
    }

    @Test
    public void test_getJobs() {
        var jobs = restService.getJobs();
        Assertions.assertNotNull(jobs);
    }
}
