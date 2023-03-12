package figo.external.jobs.official.services.dto;

import lombok.Data;

@Data
public class PinganResponse {
    private PinganJobs data;
    private String responseCode;
}
