package com.example.presentation.runing;

import com.example.presentation.model.WorkOrder;
import com.example.presentation.model.Process;
import com.example.presentation.model.Equipment;
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
public class OperationsDao {

    private final JdbcTemplate jdbcTemplate;
    private final Scanner sc = new Scanner(System.in);

    // ===== 작업지시 조회 =====
    public List<WorkOrder> findAllWorkOrders() {
        String query = "SELECT * FROM workorder";
        return jdbcTemplate.query(query, new WorkOrderRowMapper());
    }

    // ===== 작업지시 등록 =====
    public boolean insertWorkOrder(WorkOrder workOrder) {
        int result = 0;
        String query = "INSERT INTO workorder(work_order_id, product_code, plan_quantiry, status1, start_time1, end_time1) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, workOrder.getWorkOrderId(), workOrder.getProductCode(),
                    workOrder.getPlannedQuantity(), workOrder.getStatus(),
                    workOrder.getStartTime(), workOrder.getEndTime());
        } catch (Exception e) {
            log.error("작업지시 등록 실패: {}", e.getMessage());
        }
        return result > 0;
    }

    // ===== 작업지시 상태 변경 =====
    public boolean updateWorkOrderStatus() {
        int result = 0;
        System.out.print("수정할 작업지시 ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("새 상태 입력: ");
        String status = sc.nextLine();
        String query = "UPDATE workorder SET status1 = ? WHERE work_order_id = ?";
        try {
            result = jdbcTemplate.update(query, status, id);
        } catch (Exception e) {
            log.error("작업지시 상태 변경 실패: {}", e.getMessage());
        }
        return result > 0;
    }

    public WorkOrder regWorkOrder() {
        System.out.println("======== 작업지시 등록 ========");
        System.out.print("작업지시 ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("제품 코드: ");
        int productCode = Integer.parseInt(sc.nextLine());
        System.out.print("계획 수량: ");
        int plannedQuantity = Integer.parseInt(sc.nextLine());
        System.out.print("상태: ");
        String status = sc.nextLine();
        LocalDateTime now = LocalDateTime.now();
        return new WorkOrder(id, productCode, plannedQuantity, status, now, null);
    }

    // ===== 공정 조회 =====
    public List<Process> findAllProcesses() {
        String query = "SELECT * FROM process";
        return jdbcTemplate.query(query, new ProcessRowMapper());
    }

    // ===== 장비 조회 =====
    public List<Equipment> findAllEquipments() {
        String query = "SELECT * FROM equipment";
        return jdbcTemplate.query(query, new EquipmentRowMapper());
    }

    // ===== RowMapper =====
    private static class WorkOrderRowMapper implements RowMapper<WorkOrder> {
        @Override
        public WorkOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
            java.sql.Timestamp startTs = rs.getTimestamp("start_time1");
            java.sql.Timestamp endTs = rs.getTimestamp("end_time1");
            LocalDateTime startTime = (startTs != null) ? startTs.toLocalDateTime() : null;
            LocalDateTime endTime = (endTs != null) ? endTs.toLocalDateTime() : null;

            return new WorkOrder(
                    rs.getInt("work_order_id"),
                    rs.getInt("product_code"),
                    rs.getInt("plan_quantiry"),
                    rs.getString("status1"),
                    startTime,
                    endTime
            );
        }
    }

    private static class ProcessRowMapper implements RowMapper<Process> {
        @Override
        public Process mapRow(ResultSet rs, int rowNum) throws SQLException {
            java.sql.Timestamp startTs = rs.getTimestamp("start_time1");
            java.sql.Timestamp endTs = rs.getTimestamp("end_time1");
            LocalDateTime startTime = (startTs != null) ? startTs.toLocalDateTime() : null;
            LocalDateTime endTime = (endTs != null) ? endTs.toLocalDateTime() : null;

            return new Process(
                    rs.getString("process_id"),
                    rs.getInt("work_order_id"),
                    rs.getString("process_step"),
                    startTime,
                    endTime,
                    rs.getString("employee_id"),
                    rs.getString("material_code"),
                    rs.getString("hygiene")
            );
        }
    }

    private static class EquipmentRowMapper implements RowMapper<Equipment> {
        @Override
        public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Equipment(
                    rs.getString("equipment_code"),
                    rs.getString("equipment_name"),
                    rs.getString("location1"),
                    rs.getString("status1")
            );
        }
    }
}
