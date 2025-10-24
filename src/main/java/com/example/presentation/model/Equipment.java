package com.example.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    private String equipmentId;
    private String equipmentName;
    private String location;
    private String status;

    @Override
    public String toString() {
        return "Equipment_ID : " + equipmentId
                + " Name : " + equipmentName
                + " Location : " + location
                + " Status : " + status + "\n";
    }
}
