package figo.external.jobs.official.services.dto;

import lombok.Data;

@Data
public class AlibabaResponse {
    private boolean success;
    private AlibabaJobs content;
}
