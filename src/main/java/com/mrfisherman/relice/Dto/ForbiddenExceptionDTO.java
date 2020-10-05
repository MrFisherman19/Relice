package com.mrfisherman.relice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ForbiddenExceptionDTO {
    LocalDateTime timestamp = LocalDateTime.now();
    HttpStatus httpStatus = HttpStatus.FORBIDDEN;
    String message = "Insufficient authority to perform this operation.";
}
