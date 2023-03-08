package figo.extenal.jobs.official.services;

import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import figo.extenal.jobs.official.services.dto.ByteDanceJob;
import figo.extenal.jobs.official.services.dto.ByteDanceRequest;
import figo.extenal.jobs.official.services.dto.ByteDanceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class ByteDanceRestService {
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Value("${jobs.publishers.bytedance.url}")
    private String url;
    @Value("${jobs.publishers.bytedance.token}")
    private String token;

    public List<ByteDanceJob> getJobs(String keyword, int offset, int limit) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Arrays.asList(StandardCharsets.UTF_8));
        // 需要加上Host和User-Agent，否则触发跨域请求，响应405
        headers.add("Host", "jobs.bytedance.com");
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");
        RequestEntity<ByteDanceRequest> requestEntity = RequestEntity.post(URI.create(url))
                .headers(headers)
                .body(new ByteDanceRequest(token).paging(offset, limit));
        ResponseEntity<ByteDanceResponse> responseEntity = new RestTemplateBuilder().build().exchange(requestEntity, ByteDanceResponse.class);
        return responseEntity.getBody().getMessage().equalsIgnoreCase("ok") ? responseEntity.getBody().getData().getJob_post_list() : Arrays.asList();
    }
}
