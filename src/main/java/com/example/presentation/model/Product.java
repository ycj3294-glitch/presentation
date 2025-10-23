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

public class Product {
    private int product_code;
    private String product_name;
    private String product_type;
    private LocalDateTime production_date;

    @Override
    public String toString() {
        return "상품 코드 :" + product_code + " 제품명 :" + product_name + " 상품타입 :"+product_type+ " 상품 생산 시작일 : " + production_date + "\n";
    }
}
