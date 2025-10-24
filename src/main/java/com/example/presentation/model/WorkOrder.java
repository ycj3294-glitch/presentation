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
public class WorkOrder {
    private int workOrderId;
    private int productCode;
    private int plannedQuantity;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Override
    public String toString() {
        return "WorkOrder_ID : " + workOrderId
                + " Product_Code : " + productCode
                + " Planned_Quantity : " + plannedQuantity
                + " Status : " + status
                + " StartTime : " + startTime
                + " EndTime : " + endTime + "\n";
    }
}
