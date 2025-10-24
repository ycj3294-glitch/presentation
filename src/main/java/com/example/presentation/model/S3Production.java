package com.example.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class S3Production {
    String product_name;
    String material_name;
    String process_id;
    String employee_id;

    public String toString() {
        return "product_name : " + product_name +
                "\nmaterial_name : " + material_name +
                "\nprocess_id : " + process_id +
                "\nemployee_id : " + employee_id;
    }
}
