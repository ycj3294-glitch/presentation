package com.example.presentation.runing;

import com.example.presentation.model.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class InventoryDao {
    private final JdbcTemplate jdbcTemplate;

    // 창고 입고
    public boolean insertInventory(Inventory inventory) {
        String query = "INSERT INTO INVENTORY (WAREHOUSE_ID, MATERIAL_CODE, UNIT_QUANTITY, RESERVATION_RECORD, MATERIAL_INFO) VALUES (?, ?, ?, ?, ?)";
        try {
            int result = jdbcTemplate.update(query, inventory.getWareID(),
                    inventory.getMaterial_CODE(), inventory.getUnit(),
                    inventory.getStock_DATE(), inventory.getMaterial_NAME());
            return result > 0;
        } catch (Exception e) {
            log.error("창고 입고 실패 : {}", e.getMessage());
            return false;
        }
    }

    // 창고 출고
    public boolean deleteInventory(String materialCode) {
        String query = "DELETE FROM INVENTORY WHERE Material_CODE = ?";
        try {
            int result = jdbcTemplate.update(query, materialCode);
            return result > 0;
        } catch (Exception e) {
            log.error("창고 출고 실패 : {}", e.getMessage());
            return false;
        }
    }

    // 창고 재고 전체 조회
    public List<Inventory> inventoryList() {
        String query = "SELECT * FROM INVENTORY";
        return jdbcTemplate.query(query, new InventoryRowMapper());
    }

    private static class InventoryRowMapper implements RowMapper<Inventory> {
        @Override
        public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
            Inventory inventory = new Inventory();
            inventory.setWareID(rs.getString("WAREHOUSE_ID"));
            inventory.setMaterial_CODE(rs.getString("MATERIAL_CODE"));
            inventory.setUnit(rs.getString("UNIT_QUANTITY"));
            inventory.setStock_DATE(rs.getDate("RESERVATION_RECORD").toLocalDate());
            inventory.setMaterial_NAME(rs.getString("MATERIAL_INFO"));

            return inventory;
        }
    }

}
