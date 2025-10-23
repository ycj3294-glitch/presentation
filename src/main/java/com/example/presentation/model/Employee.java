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

public class Employee {
    private String id;
    private String position;
    private String department;
    private String work_shift;
    private LocalDateTime hire_date;
    private String contact_number;


    @Override
    public String toString() {
     return "Employee_ID : " + id + " Job_position : " + position + " Department : " + department + " Work_shift : " + work_shift + " Hire_Date : " + hire_date + " Contact_number : " + contact_number + "\n";
 }

}