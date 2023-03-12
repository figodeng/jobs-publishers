package figo.external.jobs.official.services.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import figo.external.jobs.core.models.Job;
import figo.external.jobs.official.constants.CompanyNameConstants;
import figo.external.jobs.official.services.JobConverter;
import lombok.Data;

import java.util.Date;

@Data
public class PinganJob implements JobConverter {
    private final String url_format = "https://talent.pingan.com/recruit/position.html?positionId=%s";
    private String businessUnitName;
    private String duty;
    private String positionId;
    private String positionShowName;
    private String qualification;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;

    @Override
    public Job convert() {
        return Job.builder().title(String.format("%s-%s", this.positionShowName, this.businessUnitName))
                .url(String.format(url_format, this.positionId))
                .description(this.qualification)
                .publishDate(this.updatedDate)
                .companyName(CompanyNameConstants.Pingan)
                .build();
    }
}
