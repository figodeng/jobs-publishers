package figo.external.jobs.official.services.dto;

import lombok.Data;

import java.util.List;

@Data
public class ByteDanceJobList {
    private List<ByteDanceJob> job_post_list;
}
