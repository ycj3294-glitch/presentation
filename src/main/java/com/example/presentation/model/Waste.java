package com.example.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Waste {
    private int waste_record_id;
    private int waste_type;
    private LocalDate waste_date;
    private String process_id;



    public String toString() {
        return "폐기 코드 : " + waste_record_id + " 폐기 제품 코드 : " + waste_type + " 폐기 일자 : " + waste_date + " 발생 공정 :" + process_id + "\n";
    }
}
