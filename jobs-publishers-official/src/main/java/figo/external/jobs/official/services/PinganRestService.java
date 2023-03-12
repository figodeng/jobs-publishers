package figo.external.jobs.official.services;

import figo.external.jobs.official.services.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class PinganRestService {
    @Value("${jobs.publishers.pingan.url}")
    private String url;

    public List<PinganJob> getJobs() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Arrays.asList(StandardCharsets.UTF_8));
        RequestEntity<PinganRequest> requestEntity = RequestEntity.post(URI.create(url))
                .headers(headers)
                .body(new PinganRequest());
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        ResponseEntity<PinganResponse> responseEntity = restTemplate.exchange(requestEntity, PinganResponse.class);
        return responseEntity.getBody().getResponseCode().equalsIgnoreCase("10001") ? responseEntity.getBody().getData().getList() : Arrays.asList();
    }
}
