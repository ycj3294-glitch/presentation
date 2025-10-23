package com.example.presentation.runing;


import com.example.presentation.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
@RequiredArgsConstructor
@Repository
@Slf4j
public class EmployeeDao {
    private final JdbcTemplate jdbcTemplate;
    private final Scanner sc = new Scanner(System.in);

    // 직원 전체 조회
    public List<Employee> employeeList() {
        String query = "SELECT * FROM EMPLOYEE";
        return jdbcTemplate.query(query, new EmployeeDao.EmployeeRowMapper());
    }
    // 직원 등록
    public boolean insertemployee(Employee employee) {
        int result = 0;
        String query = "INSERT INTO Employee(Employee_ID, Job_position, Department,  Work_shift, Contact_number) VALUES(?, ?, ?, ?, ?)";
        try {
//            result = jdbcTemplate.update(query, Employee.getId(),
//                    Employee.getPwd(), Employee.getName());

        } catch (Exception e) {
            log.error("회원 정보 추가 실패 : {}", e.getMessage());
        }
        return result > 0;
    }
    // 직원 정보 수정
    // 직원 삭제
    // 이전으로

    private static class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(
                    rs.getString("Employee_ID"),
                    rs.getString("Job_position"),
                    rs.getString("Department"),
                    rs.getString("Work_shift"),
                    rs.getTimestamp("Hire_Date").toLocalDateTime(),
                    rs.getString("Contact_number")
            );
        }
    }
}

