package figo.external.jobs.official.services;

import figo.external.jobs.official.services.dto.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlibabaRestService {
    private final String url = "https://talent.alibaba.com/position/search";
    private final String urlOffCampus = "https://talent.alibaba.com/off-campus/home?lang=zh";

    public List<AlibabaJob> getJobs() {
        HashMap<String, String> cookies = getCookies();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(Arrays.asList(StandardCharsets.UTF_8));
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");
        headers.add("cookie", buildCookies(cookies));
        RequestEntity<AlibabaRequest> requestEntity = RequestEntity.post(URI.create(String.format("%s?_csrf=%s", url, cookies.get("XSRF-TOKEN"))))
                .headers(headers)
                .body(new AlibabaRequest());
        ResponseEntity<AlibabaResponse> responseEntity = new RestTemplateBuilder().build().exchange(requestEntity, AlibabaResponse.class);
        return responseEntity.getBody().isSuccess() ? responseEntity.getBody().getContent().getDatas() : Arrays.asList();
    }

    public HashMap<String, String> getCookies() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Host", "talent.alibaba.com");
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");
        RequestEntity requestEntity = RequestEntity.get(URI.create(urlOffCampus))
                .headers(headers).build();
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        HashMap<String, String> cookies = getCookies(responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE));
        return cookies;
    }

    private String buildCookies(HashMap<String, String> cookies) {
        List<String> list = cookies.entrySet().stream().map(p -> String.format("%s=%s", p.getKey(), p.getValue()))
                .collect(Collectors.toList());
        return String.join(";", list);
    }

    private HashMap<String, String> getCookies(List<String> strs) {
        HashMap<String, String> cookies = new HashMap<>();
        String[] list, keyVal;
        for (String str : strs) {
            list = str.split(";");
            for (String s : list) {
                if ((keyVal = s.split("=")).length == 2 && !"/".equals(keyVal[1])) {
                    cookies.put(keyVal[0], keyVal[1]);
                }
            }
        }
        return cookies;
    }
}
