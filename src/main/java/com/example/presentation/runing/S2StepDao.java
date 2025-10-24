package com.example.presentation.runing;

import com.example.presentation.model.S1Production;
import com.example.presentation.model.S2Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Repository
@Slf4j

public class S2StepDao {
    private final JdbcTemplate jdbcTemplate;
    private final Scanner sc = new Scanner(System.in);

    // s2 조회
    public List<S2Step> s2StepList() {
        System.out.println("해당 공정 입력(재료준비, 생산, 포장) : ");
        String code = sc.nextLine();
        String query = "SELECT" +
                " P.PRODUCT_NAME, PRO.PROCESS_STEP, PRO.END_TIME1 " +
                "FROM PRODUCT1 P LEFT JOIN WORKORDER W ON P.PRODUCT_CODE = W.PRODUCT_CODE " +
                "LEFT JOIN PROCESS PRO ON W.WORK_ORDER_ID = PRO.WORK_ORDER_ID " +
                "WHERE PRO.PROCESS_STEP = ?";
        List<S2Step> list = jdbcTemplate.query(query, new Object[]{code}, new S2StepDao.S2StepRowMapper());
        return list;
    }

        // 맵핑
        private static class S2StepRowMapper implements RowMapper<S2Step> {

            @Override
            public S2Step mapRow(ResultSet rs, int rowNum) throws SQLException {
                java.sql.Timestamp ts = rs.getTimestamp("END_TIME1");
                java.time.LocalDateTime EndDate = (ts != null) ? ts.toLocalDateTime() : LocalDateTime.now();
                return new S2Step(
                        rs.getString("PRODUCT_NAME"),
                        rs.getString("PROCESS_STEP"),
                        EndDate
                        );
            }
        }
    }

