package com.example.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor // 매개변수가 전부 있는 생성자
@NoArgsConstructor // 매개변수가 하나도 없는 생성자
public class Member {
    private String email;
    private String pwd;
    private String name;
    private LocalDateTime regDate;

    @Override
    public String toString() {
        return  "이메일 : " + email + "\n" +
                "비밀번호 : " + pwd + "\n" +
                "이름 : " + name + "\n" +
                "가입일 : "+ regDate + "\n";
    }
}
