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

public class S2Step {
    String product_name;
    String process_step;
    LocalDateTime end_time;

    @Override
    public String toString() {
        return  "product_name : " + product_name +
        "\nprocess_step : " + process_step +
        "\nend_time : " + end_time +
        "\n-------------------------------------";
    }
}
