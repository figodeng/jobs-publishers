package figo.external.jobs.official.services;

import figo.external.jobs.official.services.dto.TencentJob;
import figo.external.jobs.official.services.dto.TencentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TencentRestService {
    @Value("${jobs.publishers.tencent.url}")
    private String url;

    public List<TencentJob> getJobs() {
        return new RestTemplateBuilder().build().getForObject(url, TencentResponse.class)
                .getData()
                .getJobs();
    }
}
