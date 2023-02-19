package figo.external.jobs.core.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Job {
    private String url;

    private String companyName;

    private String title;

    private String description;

    private Date publishDate;
}
