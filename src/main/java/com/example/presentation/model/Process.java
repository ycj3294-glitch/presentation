package com.example.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Process {
    private String processId;
    private int workOrderId;
    private String processStep;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String assignedEmployee;
    private String material_code;
    private String hygieneStatus;

    @Override
    public String toString() {
        return "Process_ID : " + processId
                + " Step : " + processStep
                + " WorkOrder_ID : " + workOrderId
                + " StartTime : " + startTime
                + " EndTime : " + endTime
                + " Employee : " + assignedEmployee
                + " Material_Code" + material_code
                + " Hygiene : " + hygieneStatus + "\n";
    }
}
