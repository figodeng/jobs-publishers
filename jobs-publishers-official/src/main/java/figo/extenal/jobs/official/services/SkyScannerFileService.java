package figo.extenal.jobs.official.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import figo.extenal.jobs.official.constants.SkyScannerConstants;
import figo.extenal.jobs.official.services.dto.SkyScannerJob;
import figo.extenal.jobs.official.services.dto.SkyScannerJobList;
import figo.external.jobs.core.models.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
