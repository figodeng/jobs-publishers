package figo.external.jobs.official.services;

import figo.external.jobs.official.services.dto.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class ViVoRestService {
    private final String url = "https://career.oppo.com/ats-candidate-api/open-api/position/queryPositionList";

    public List<ViVoJob> getJobs() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Arrays.asList(StandardCharsets.UTF_8));
        RequestEntity<ViVoRequest> requestEntity = RequestEntity.post(URI.create(url))
                .headers(headers)
                .body(new ViVoRequest());
        ResponseEntity<ViVoResponse> responseEntity = new RestTemplateBuilder().build().exchange(requestEntity, ViVoResponse.class);
        return responseEntity.getBody().getCode().equalsIgnoreCase("0") ? responseEntity.getBody().getData().getList() : Arrays.asList();

    }
}
