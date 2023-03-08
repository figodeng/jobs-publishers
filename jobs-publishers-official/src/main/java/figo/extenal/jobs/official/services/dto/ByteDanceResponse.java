package figo.extenal.jobs.official.services.dto;

import lombok.Data;

@Data
public class ByteDanceResponse {
    private String message;

    private ByteDanceJobList data;
}
