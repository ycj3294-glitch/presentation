package com.example.presentation.runing;

import com.example.presentation.model.Employee;
import com.example.presentation.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;
    private final Scanner sc = new Scanner(System.in);

    // 상품 조회
    public List<Product> productList() {
        String query = "SELECT * FROM PRODUCT1";
        return jdbcTemplate.query(query, new ProductDao.ProductRowMapper());
    }
    // 상품 등록
    public boolean insertproduct(Product product) {
        int result = 0;
        String query = "INSERT INTO PRODUCT1(PRODUCT_CODE, PRODUCT_NAME, PRODUCT_TYPE,  PRODUCTION_DATE) VALUES(?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, product.getProduct_code(),
                    product.getProduct_name(), product.getProduct_type(),
                    Timestamp.valueOf(LocalDateTime.now()));
                    ;

        } catch (Exception e) {
            log.error("상품 정보 추가 실패 : {}", e.getMessage());
        }
        return result > 0;
    }
    // 상품 수정
    public boolean updateProduct() {
        int result = 0;
        System.out.print("수정할 상품 코드: ");
        int product_code = sc.nextInt();
        sc.nextLine();
        System.out.print("수정된 상품 코드: ");
        int uproduct_code = sc.nextInt();
        sc.nextLine();
        System.out.print("상품명: ");
        String uproduct_name = sc.nextLine();
        System.out.print("상품타입: ");
        String uproduct_type = sc.nextLine();
        String query = "UPDATE PRODUCT1 SET PRODUCT_CODE = ?, PRODUCT_NAME = ?, PRODUCT_TYPE = ? WHERE PRODUCT_CODE = ?";
        try {
            result = jdbcTemplate.update(query, uproduct_code, uproduct_name, uproduct_type, product_code);

        } catch (Exception e) {
            log.error("상품 정보 수정 실패 : {}", e.getMessage());
        }
        return result > 0;
    }
    // 상품 삭제
    public boolean removeProduct() {
        int result = 0;
        System.out.println("삭제할 대상 상품 코드 : ");
        int product_code = sc.nextInt();
        sc.nextLine();
        String query = "DELETE FROM PRODUCT1 WHERE PRODUCT_CODE = ?";
        try {
            result = jdbcTemplate.update(query, product_code);

        } catch (Exception e) {
            log.error("상품 삭제 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    public Product regProduct() {
        System.out.println("======== 상품 등록 ========");
        System.out.print("상품 코드: ");
        int product_code = sc.nextInt();
        sc.nextLine();
        System.out.print("상품명: ");
        String product_name = sc.nextLine();
        System.out.print("상품 타입: ");
        String product_type = sc.nextLine();
        return new Product(product_code, product_name, product_type, null);
    }

    // 맵핑
    private static class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            java.sql.Timestamp ts = rs.getTimestamp("PRODUCTION_DATE");
            java.time.LocalDateTime hireDate = (ts != null) ? ts.toLocalDateTime() : LocalDateTime.now();
            return new Product(
                    rs.getInt("PRODUCT_CODE"),
                    rs.getString("PRODUCT_NAME"),
                    rs.getString("PRODUCT_TYPE"),
                    hireDate
            );
        }
    }
}
