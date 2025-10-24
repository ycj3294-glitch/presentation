package com.example.presentation.runing;


import com.example.presentation.model.Performance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
@RequiredArgsConstructor
@Repository
@Slf4j

public class PerformDao {
    private final JdbcTemplate jdbcTemplate;


    // 설비상태 전체 조회
    public List<Performance> performList() {
        String query = "SELECT * FROM PERFORMANCE1";
        return jdbcTemplate.query(query, new PerformanceRowMapper());
    }

    private static class PerformanceRowMapper implements RowMapper<Performance> {
        @Override
        public Performance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Performance performance = new Performance();
            performance.setPerformanceId(rs.getString("PERFORMANCE_ID"));
            performance.setEquipmentCode(rs.getString("EQUIPMENT_CODE"));
            performance.setWorkOrderId(rs.getInt("WORK_ORDER_ID"));
            performance.setStartTime1(rs.getTimestamp("START_TIME1") != null ? rs.getTimestamp("START_TIME1").toLocalDateTime() : null);
            performance.setEndTime1(rs.getTimestamp("END_TIME1") != null ? rs.getTimestamp("END_TIME1").toLocalDateTime() : null);
            performance.setPerformanceMetric(rs.getString("PERFORMANCE_METRIC"));
            performance.setOutput(rs.getInt("OUTPUT"));
            return performance;
        }
    }
}
