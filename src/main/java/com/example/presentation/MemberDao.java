package com.example.presentation;

import com.example.presentation.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Repository // Spring container Bean 등록
@Slf4j
@RequiredArgsConstructor // 생성자를 통해서 의존성 주입을 받음
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;
    private final Scanner sc = new Scanner(System.in);

    // 회원 전체 조회
    public List<Member> memberList() {
        String query = "SELECT * FROM member";
        return jdbcTemplate.query(query, new MemberRowMapper());
    }
    // 회원 등록
    public boolean insertMember(Member member) {
        int result = 0;
        String query = "INSERT INTO member(email, pwd, name) VALUES(?, ?, ?)";
        try {
            result = jdbcTemplate.update(query, member.getEmail(),
                    member.getPwd(), member.getName());

        } catch (Exception e) {
            log.error("회원 정보 추가 실패 : {}", e.getMessage());
        }
        return result > 0;
    }
    // 회원 수정
    public boolean updateMember() {
        int result = 0;
        System.out.println("수정할 대상 이메일 : ");
        String email = sc.nextLine();
        System.out.println("수정할 이메일 : ");
        String uemail = sc.nextLine();
        System.out.println("수정할 비밀번호 : ");
        String upwd = sc.nextLine();
        System.out.println("수정할 이름 : ");
        String uname = sc.nextLine();
        String query = "UPDATE member SET EMAIL = ?, PWD = ?, NAME = ? WHERE EMAIL = ?";
        try {
            result = jdbcTemplate.update(query, uemail, upwd, uname, email);

        } catch (Exception e) {
            log.error("회원정보 수정 실패 : {}", e.getMessage());
        }
        return result > 0;
    }

    // 회원 삭제
    public boolean removeMember() {
        int result = 0;
        System.out.println("삭제할 대상 이메일 : ");
        String email = sc.nextLine();
        String query = "DELETE FROM member WHERE EMAIL = ?";
        try {
            result = jdbcTemplate.update(query, email);

        } catch (Exception e) {
            log.error("회원 삭제 실패 : {}", e.getMessage());
        }
        return result > 0;
    }


    private static class MemberRowMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(
                    rs.getString("email"),
                    rs.getString("pwd"),
                    rs.getString("name"),
                    rs.getTimestamp("reg_date").toLocalDateTime()
            );
        }
    }

}
