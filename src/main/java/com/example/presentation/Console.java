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
            System.out.println("[1]기준 정보 관리 [2]생산 관리 [3]자재/설비 관리 [4]폐기 관리 [5]종료");
            int choice1 = sc.nextInt();
            sc.nextLine();
            switch (choice1) {
                // 기준 정보 관리
                case 1 :
                    System.out.println("[1]상품 관리 [2]   [3]종료");

                    System.out.println("[1]직원 전체 조회 [2]직원 등록 [3]직원 정보 수정 [4]직원 정보 삭제 [5]종료");
                    int choice2 = sc.nextInt();
                    sc.nextLine();
                    switch (choice2) {
                        case 1 :
                            List<Employee> employeeList = employeeDao.employeeList();
                            employeeDao.employeeList();
                            System.out.println("======== 직원 목록 조회 =========");
                            for(Employee employee : employeeList) System.out.println(employee);
                            break;
                        case 2 :
                            boolean isSuccess = employeeDao.insertemployee(employeeDao.regEmployee());
                            System.out.println("직원 등록 : " + (isSuccess ? "성공" : "실패"));
                            break;
                        case 3 :
                            boolean isUpdate = employeeDao.updateEmployee();
                            System.out.println("직원 정보 수정 : " + (isUpdate ? "성공" : "실패"));
                            break;
                        case 4 :
                            boolean isDelete = employeeDao.removeEmployee();
                            System.out.println("직원삭제 : " + (isDelete ? "성공" : "실패"));
                            break;
                        case 5 :
                            return;
                    }
                // 생산 관리
                case 2 :
                // 자재/설비 관리
                case 3 :
                // 폐기 관리
                case 4 :
                // 종료
                case 5 : return;
            }

        }

    }

}