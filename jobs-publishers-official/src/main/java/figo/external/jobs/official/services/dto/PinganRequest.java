package figo.external.jobs.official.services.dto;

import lombok.Data;

@Data
public class PinganRequest {
    private String keyword;
    private String businessUnitId;
    private String addressCode;
    private String postCategory;
    private boolean wecruitPlatform;
    private String tenantId;
    private int pageNum;
    private int pageSize;
    private boolean countTotal;

    public PinganRequest() {
        this.keyword = "java";
        this.businessUnitId = "";
        this.addressCode = "0/4/396/399";
        this.postCategory = "ITN";
        this.tenantId = "CHDUIE8QRPG16AJFM2B9NL0OS3TK574";
        this.pageNum = 1;
        this.pageSize = 1000;
        this.countTotal = true;
    }
}
