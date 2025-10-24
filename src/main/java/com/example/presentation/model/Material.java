package com.example.presentation.model;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Material {
    private String Material_CODE;
    private String Material_NAME;
    private int Unit;
    private LocalDate Expiration_DATE;

}