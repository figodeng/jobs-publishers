package figo.external.jobs.official.services.dto;

import lombok.Data;

import java.util.List;

@Data
public class SkyScannerJobList {
    private List<SkyScannerJob> jobs;
}
