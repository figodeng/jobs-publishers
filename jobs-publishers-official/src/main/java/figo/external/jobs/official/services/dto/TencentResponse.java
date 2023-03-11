package figo.external.jobs.official.services.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TencentResponse {
    @JsonProperty("Data")
    private TencentJobs data;
}
