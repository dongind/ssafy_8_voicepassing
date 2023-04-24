package com.ssafy.voicepassing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResultDto{
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Result {
        private int resultId;
        private String androidId;
        private int risk;
        private int category;
        private String text;

    }
}
