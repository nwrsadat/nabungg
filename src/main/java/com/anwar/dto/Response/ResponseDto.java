package com.anwar.dto.Response;

import lombok.*;

@Data
public class ResponseDto {
    private final int status;
    private final Object response;
    private final long timestamp;
}
