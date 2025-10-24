package com.example.presentation.runing;

import com.example.presentation.model.Waste;
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

public class WasteDao {
    private final JdbcTemplate jdbcTemplate;
    private final Scanner sc = new Scanner(System.in);

    // 폐기 현황
    public List<Waste> wasteList() {
        String query = "SELECT * FROM WASTE";
        List<Waste> list = jdbcTemplate.query(query, new WasteDao.WasteRowMapper());
        jdbcTemplate.query(query, new WasteDao.WasteRowMapper());
        return list;
    }

    // 폐기 등록.. 연관 테이블에 존재하는 데이터만 입력 가능함
    public boolean insertwaste(Waste waste) {
        int result = 0;
        String query = "INSERT INTO WASTE(WASTE_RECORD_ID, WASTE_TYPE, WASTE_DATE, PROCESS_ID) VALUES(?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, waste.getWaste_record_id(),
                    waste.getWaste_type(), waste.getWaste_date(),
                    waste.getProcess_id());


        } catch (Exception e) {
            log.error("상품 정보 추가 실패 : {}", e.getMessage());
        }
        return result > 0;
    }
    // 입력
    public Waste regWaste() {
        System.out.println("======== 폐기 등록 ========");
        System.out.print("폐기 아이디(int): ");
        int waste_record_id = sc.nextInt();
        sc.nextLine();
        System.out.print("폐기 타입(상품 코드 1~5): ");
        int waste_type = sc.nextInt();
        sc.nextLine();
        System.out.print("폐기 일자(yyyy-mm-dd): ");
        String input = sc.nextLine();
        LocalDate waste_date = LocalDate.parse(input);
        System.out.print("폐기 공정(process_ID PROC001~7): ");
        String process_id = sc.nextLine();
        return new Waste(waste_record_id, waste_type, waste_date, process_id);
    }




    // 맵핑

    private static class WasteRowMapper implements RowMapper<Waste> {

        @Override
        public Waste mapRow(ResultSet rs, int rowNum) throws SQLException {
            java.sql.Timestamp ts = rs.getTimestamp("WASTE_DATE");
            java.time.LocalDate wasteDate = (ts != null) ? ts.toLocalDateTime().toLocalDate() : LocalDate.now();
            return new Waste(
                    rs.getInt("WASTE_RECORD_ID"),
                    rs.getInt("WASTE_TYPE"),
                    wasteDate,
                    rs.getString("PROCESS_ID")
            );
        }
    }
}
