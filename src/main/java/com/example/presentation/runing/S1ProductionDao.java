package com.example.presentation.runing;

import com.example.presentation.model.S1Production;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Repository
@Slf4j

public class S1ProductionDao {
    private final JdbcTemplate jdbcTemplate;
    private final Scanner sc = new Scanner(System.in);

    // 쿼리 쏘고 기능 구현
    public List<S1Production> s1production() {
        System.out.println("해당 불량 코드 입력 : ");
        int code = sc.nextInt();
        sc.nextLine();
        String query = "SELECT " +
                "W.WASTE_RECORD_ID, W.WASTE_DATE, P.PROCESS_STEP, " +
                "E.EQUIPMENT_NAME, EMP.EMPLOYEE_ID, EMP.JOB_POSITION, " +
                "EMP.DEPARTMENT " +
                "FROM WASTE W " +
                "JOIN PROCESS P ON W.PROCESS_ID = P.PROCESS_ID " +
                "JOIN PERFORMANCE1 PF ON P.WORK_ORDER_ID = PF.WORK_ORDER_ID " +
                "JOIN EQUIPMENT E ON PF.EQUIPMENT_CODE = E.EQUIPMENT_CODE " +
                "JOIN EMPLOYEE EMP ON P.EMPLOYEE_ID = EMP.EMPLOYEE_ID " +
                "WHERE W.WASTE_RECORD_ID = ?";
        List<S1Production> list = jdbcTemplate.query(query, new Object[]{code}, new S1ProductionDao.ProductionRowMapper());
        return list;
    }



    private static class ProductionRowMapper implements RowMapper<S1Production> {

        @Override
        public S1Production mapRow(ResultSet rs, int rowNum) throws SQLException {
            java.sql.Timestamp ts = rs.getTimestamp("WASTE_DATE");
            java.time.LocalDate wasteDate = (ts != null) ? ts.toLocalDateTime().toLocalDate() : LocalDate.now();
            return new S1Production(
                    rs.getInt("WASTE_RECORD_ID"),
                    wasteDate,
                    rs.getString("PROCESS_STEP"),
                    rs.getString("EQUIPMENT_NAME"),
                    rs.getString("EMPLOYEE_ID"),
                    rs.getString("JOB_POSITION"),
                    rs.getString("DEPARTMENT")
                    );
        }
    }

}
