package figo.external.jobs.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Recruitment {
    private String publisher;
    private List<Job> jobs;
}
