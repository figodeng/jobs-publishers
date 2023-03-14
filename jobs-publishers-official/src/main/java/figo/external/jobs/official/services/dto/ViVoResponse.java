package figo.external.jobs.official.services.dto;

import lombok.Data;

@Data
public class ViVoResponse {
    private String code;

    private ViVoJobs data;
}
