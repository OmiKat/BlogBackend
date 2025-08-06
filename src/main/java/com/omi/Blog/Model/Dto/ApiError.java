package com.omi.Blog.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private int status;
    private String message;
    private List<Field> errors;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Field{
        private String field;
        private String message;
    }
}
