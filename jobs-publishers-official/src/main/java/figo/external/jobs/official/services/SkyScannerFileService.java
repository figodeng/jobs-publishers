package figo.external.jobs.official.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import figo.external.jobs.official.services.dto.SkyScannerJobList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Slf4j
public class SkyScannerFileService implements SkyScannerService {
    private final static String FileName = "skyscanner.json";

    public SkyScannerFileService() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper = mapper;
    }

    private ObjectMapper objectMapper;

    @Override
    public SkyScannerJobList getJobs() {
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(FileName)) {
            SkyScannerJobList jobList = objectMapper.readValue(inputStream, SkyScannerJobList.class);
            return jobList;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
