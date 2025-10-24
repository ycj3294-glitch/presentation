package com.example.presentation.runing;

import com.example.presentation.model.S3Production;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Repository
@Slf4j
@RequiredArgsConstructor

public class S3ProductionDao {
    private final JdbcTemplate jdbcTemplate;
    private final Scanner sc = new Scanner(System.in);

    // 빵 관련 재료 공정 작업자 조회
    public List<S3Production> s3ProductionList() {
        System.out.println("생산 제품 이름을 입력 : ");
        String name = sc.nextLine();
        String query ="SELECT P1.PRODUCT_NAME, M.MATERIAL_NAME, PRO.PROCESS_ID, E.EMPLOYEE_ID\n" +
                "FROM PRODUCT1 P1 LEFT JOIN WORKORDER WO\n" +
                "ON P1.PRODUCT_CODE = WO.PRODUCT_CODE\n" +
                "JOIN PROCESS PRO ON WO.WORK_ORDER_ID = PRO.WORK_ORDER_ID\n" +
                "JOIN MATERIAL M ON PRO.MATERIAL_CODE = M.MATERIAL_CODE\n" +
                "JOIN EMPLOYEE E ON PRO.EMPLOYEE_ID = E.EMPLOYEE_ID\n" +
                "WHERE P1.PRODUCT_NAME = ?";
        List<S3Production> list = jdbcTemplate.query(query, new S3ProductionDao.S3productionRowMapper(), name);
        return list;

    }





    //맵핑
    private static class S3productionRowMapper implements RowMapper<S3Production> {

        @Override
        public S3Production mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new S3Production(
                    rs.getString("PRODUCT_NAME"),
                    rs.getString("MATERIAL_NAME"),
                    rs.getString("PROCESS_ID"),
                    rs.getString("EMPLOYEE_ID")
            );
        }
    }
}
