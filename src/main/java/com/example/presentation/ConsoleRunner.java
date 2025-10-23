package com.example.presentation;


import com.example.presentation.MemberDao;
import com.example.presentation.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
// 콘솔 입력 만들기 위한 파일
@Component
@RequiredArgsConstructor

public class ConsoleRunner implements CommandLineRunner {
    private final Scanner sc = new Scanner(System.in);
    private final MemberDao memberDao;
    @Override
    public void run(String... args) throws Exception {
        while(true) {
            System.out.println("====== 콘솔 회원 관리 시스템 ======");
            System.out.println("[1]회원 등록");
            System.out.println("[2]회원 목록 조회");
            System.out.println("[3]회원 수정");
            System.out.println("[4]회원 삭제");
            System.out.println("[5]종료");
            int sel = sc.nextInt();
            sc.nextLine();
            switch (sel) {
                case 1 :
                    boolean isSuccess = memberDao.insertMember(regMember());
                    System.out.println("회원가입 : " + (isSuccess ? "성공" : "실패"));
                    break;
                case 2 :
                    List<Member> memberList = memberDao.memberList();
                    System.out.println("======== 회원 목록 조회 =========");
                    for(Member member : memberList) System.out.println(member);
                    break;
                case 3 :
                    boolean isUpdate = memberDao.updateMember();
                    System.out.println("회원 정보 수정 : " + (isUpdate ? "성공" : "실패"));
                    break;
                case 4 :
                    //회원 삭제
                    // 대상 지정을 뭘 기준으로 하게 하는가? sql문에서 삭제는 DELETE
                    // 테이블 이름 member
                    // 기준으로 할 컬럼명 이메일 비밀번호 이름 날짜
                    boolean isDelete = memberDao.removeMember();
                    System.out.println("회원삭제 : " + (isDelete ? "성공" : "실패"));
                    break;
                case 5 :
                    return;

            }
        }

    }
    private Member regMember() {
        System.out.println("======== 회원 등록 ========");
        System.out.print("이메일: ");
        String email = sc.nextLine();
        System.out.print("비밀번호: ");
        String pwd = sc.nextLine();
        System.out.print("이름: ");
        String name = sc.nextLine();
        return new Member(email, pwd, name, null);
    }

}
