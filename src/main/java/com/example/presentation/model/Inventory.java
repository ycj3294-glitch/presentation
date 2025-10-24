package com.example.presentation.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Inventory {
    private String wareID;
    private String Material_CODE;
    private String Unit;
    private LocalDate Stock_DATE;
    private String Material_NAME;

}
