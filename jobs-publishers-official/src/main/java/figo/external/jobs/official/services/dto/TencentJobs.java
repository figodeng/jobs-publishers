package figo.external.jobs.official.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TencentJobs {
    @JsonProperty(value = "Posts")
    private List<TencentJob> jobs;
}
