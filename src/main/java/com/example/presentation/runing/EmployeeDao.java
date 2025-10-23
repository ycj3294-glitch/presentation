package com.example.presentation.runing;


import com.example.presentation.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
            result = jdbcTemplate.update(query, employee.getId(),
                    employee.getPosition(), employee.getDepartment(),
                    employee.getWork_shift(), employee.getContact_number());

        } catch (Exception e) {
            log.error("회원 정보 추가 실패 : {}", e.getMessage());
        }
        return result > 0;
    }
    // 직원 정보 수정
    public boolean updateEmployee() {
        int result = 0;
        System.out.print("수정할 사원 번호: ");
        String id = sc.nextLine();
        System.out.print("수정된 사원 번호: ");
        String uid = sc.nextLine();
        System.out.print("직책: ");
        String uposition = sc.nextLine();
        System.out.print("부서: ");
        String udepartment = sc.nextLine();
        System.out.print("근무시간: ");
        String uwork_shift = sc.nextLine();
        System.out.print("전화번호: ");
        String ucontact_number = sc.nextLine();
        String query = "UPDATE Employee SET Employee_ID = ?, Job_Position = ?, Department = ?, Work_Shift = ?, Contact_Number = ? WHERE Employee_ID = ?";
        try {
            result = jdbcTemplate.update(query, uid, uposition, udepartment, uwork_shift, ucontact_number, id);

        } catch (Exception e) {
            log.error("회원정보 수정 실패 : {}", e.getMessage());
        }
        return result > 0;
    }
    // 직원 삭제
    public boolean removeEmployee() {
        int result = 0;
        System.out.println("삭제할 대상 사원번호 : ");
        String id = sc.nextLine();
        String query = "DELETE FROM Employee WHERE Employee_ID = ?";
        try {
            result = jdbcTemplate.update(query, id);

        } catch (Exception e) {
            log.error("회원 삭제 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    public Employee regEmployee() {
        System.out.println("======== 회원 등록 ========");
        System.out.print("사원 번호: ");
        String id = sc.nextLine();
        System.out.print("직책: ");
        String position = sc.nextLine();
        System.out.print("부서: ");
        String department = sc.nextLine();
        System.out.print("근무시간: ");
        String work_shift = sc.nextLine();
        System.out.print("전화번호: ");
        String contact_number = sc.nextLine();
        return new Employee(id, position, department, work_shift, null, contact_number);
    }
    // 이전으로

    private static class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            java.sql.Timestamp ts = rs.getTimestamp("Hire_Date");
            java.time.LocalDateTime hireDate = (ts != null) ? ts.toLocalDateTime() : LocalDateTime.now();
            return new Employee(
                    rs.getString("Employee_ID"),
                    rs.getString("Job_position"),
                    rs.getString("Department"),
                    rs.getString("Work_shift"),
                    hireDate,
                    rs.getString("Contact_number")
            );
        }
    }
}

