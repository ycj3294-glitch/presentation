package com.example.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class S1Production {
    int waste_record;
    LocalDate waste_date;
    String process_step;
    String equipment_name;
    String employee_id;
    String job_position;
    String department;


    @Override
    public String toString() {
        return "waste_record : " + waste_record + "\nwaste_date : "+ waste_date +
                "\nprocess_step : " + process_step + "\nequipment_name : " + equipment_name +
                "\nemployee_id : " + employee_id + "\njob_position : " + job_position +
                "\n department : " + department;

    }
}
