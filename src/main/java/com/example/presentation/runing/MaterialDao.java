package com.example.presentation.runing;


import com.example.presentation.model.Material;
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
public class MaterialDao {
    private final JdbcTemplate jdbcTemplate;
    private final Scanner sc = new Scanner(System.in);

    // 자재 등록
    public boolean insertMaterial(Material material) {
        String query = "INSERT INTO MATERIAL (Material_CODE, Material_NAME, Unit, Expiration_DATE) VALUES (?, ?, ?, ?)";
        try {
            int result = jdbcTemplate.update(query, material.getMaterial_CODE(),
                    material.getMaterial_NAME(), material.getUnit(),
                    material.getExpiration_DATE());
            return result > 0;
        } catch (Exception e) {
            log.error("자재 등록 실패 : {}", e.getMessage());
            return false;
        }
    }

    // 자재 수정 (자재 코드로 특정 자재를 찾아 정보 수정)
    public boolean updateMaterial(Material material) {
        String query = "UPDATE MATERIAL SET Material_NAME = ?, Unit = ?, Expiration_DATE = ? WHERE Material_CODE = ?";
        try {
            int result = jdbcTemplate.update(query,
                    material.getMaterial_NAME(), material.getUnit(),
                    material.getExpiration_DATE(), material.getMaterial_CODE());
            return result > 0;
        } catch (Exception e) {
            log.error("자재 정보 수정 실패 : {}", e.getMessage());
            return false;
        }
    }

    // 자재 삭제 (자재 코드로 삭제)
    public boolean deleteMaterial(String materialCode) {
        String query = "DELETE FROM MATERIAL WHERE Material_CODE = ?";
        try {
            int result = jdbcTemplate.update(query, materialCode);
            return result > 0;
        } catch (Exception e) {
            log.error("자재 삭제 실패 : {}", e.getMessage());
            return false;
        }
    }

    // 자재 전체 조회
    public List<Material> materialList() {
        String query = "SELECT * FROM MATERIAL";
        return jdbcTemplate.query(query, new MaterialRowMapper());
    }

    // 유통기한 임박 자재 조회 (예: 오늘부터 days일 이내)
    public List<Material> findMaterialsExpiringWithin(int days) {
        String query = "SELECT * FROM MATERIAL WHERE Expiration_DATE BETWEEN CURRENT_DATE AND (CURRENT_DATE + ?)";
        return jdbcTemplate.query(query, new Object[]{days}, new MaterialRowMapper());
    }


    private static class MaterialRowMapper implements RowMapper<Material> {
        @Override
        public Material mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Material(
                    rs.getString("Material_CODE"),
                    rs.getString("Material_NAME"),
                    rs.getInt("UNIT"),
                    rs.getDate("Expiration_DATE").toLocalDate()

            );
        }
    }
}

