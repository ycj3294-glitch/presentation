package com.example.presentation.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Performance {
    private String performanceId;
    private String equipmentCode;
    private int workOrderId;
    private LocalDateTime startTime1;
    private LocalDateTime endTime1;
    private String performanceMetric;
    private int output;

}
