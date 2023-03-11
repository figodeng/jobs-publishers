package figo.external.jobs.official.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import figo.external.jobs.core.models.Job;
import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.JobConverter;
import figo.external.jobs.official.utils.DateUtils;
import lombok.Data;

import java.util.Date;

@Data
public class TencentJob implements JobConverter {
    @JsonProperty(value = "RecruitPostName")
    private String title;
    @JsonProperty(value = "Responsibility")
    private String description;
    @JsonProperty(value = "PostURL")
    private String url;
    @JsonProperty("IsValid")
    private boolean valid;
    // 2023年03月08日
    @JsonProperty(value = "LastUpdateTime")
    private String lastUpdateTime;

    @Override
    public Job convert() {
        return Job.builder().title(this.title)
                .url(this.url)
                .description(this.description)
                .publishDate(DateUtils.convert(this.lastUpdateTime))
                .companyName(CompanyNameConstants.Tencent)
                .build();
    }
}
