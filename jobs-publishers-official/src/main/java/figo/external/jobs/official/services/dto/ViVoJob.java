package figo.external.jobs.official.services.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import figo.external.jobs.core.models.Job;
import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.JobConverter;
import lombok.Data;

import java.util.Date;

@Data
public class ViVoJob implements JobConverter {
    private final String url_format = "https://career.oppo.com/pc/post/detail?positionId=%s";
    private String positionId;
    private String jobName;
    private String workRequire;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    @Override
    public Job convert() {
        return Job.builder().title(this.jobName)
                .url(String.format("%s%s", this.url_format, this.positionId))
                .description(this.workRequire)
                .publishDate(this.publishDate)
                .companyName(CompanyNameConstants.ViVo)
                .build();
    }
}
