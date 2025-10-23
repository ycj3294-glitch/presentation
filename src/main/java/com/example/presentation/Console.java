package com.example.presentation;

import com.example.presentation.model.Employee;
import com.example.presentation.runing.EmployeeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
@RequiredArgsConstructor
@Component
public class Console implements CommandLineRunner {
    private final Scanner sc = new Scanner(System.in);
    private final EmployeeDao employeeDao;
    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("====== 콘솔 공장 관리 시스템 ======");
            System.out.println("[1]직원 정보 조회");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 :
                    List<Employee> employeeList = employeeDao.employeeList();
                    employeeDao.employeeList();
                    System.out.println("======== 회원 목록 조회 =========");
                    for(Employee employee : employeeList) System.out.println(employee);
                    break;
            }
        }
    }
}