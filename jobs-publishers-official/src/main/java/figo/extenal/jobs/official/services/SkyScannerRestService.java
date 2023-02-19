package figo.extenal.jobs.official.services;

import figo.extenal.jobs.official.services.dto.SkyScannerJobList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SkyScannerRestService implements SkyScannerService {
    @Value("${jobs.publishers.skyscanner.url}")
    private String url;

    public SkyScannerJobList getJobs() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();

        return restTemplate.getForObject(url, SkyScannerJobList.class);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
