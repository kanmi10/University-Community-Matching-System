package com.project.ssm.matching;

import java.util.Scanner;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginInterface;
import com.project.ssm.login.LoginService;
import com.project.ssm.user.User;

/**
 * 스터디 매칭화면 클래스입니다.
 *
 * @author 김경현, 김유진
 */
public class StudyMatch implements Matching {

    private final MatchingUser matchingUser;

    public StudyMatch(MatchingUser matchingUser) {
        this.matchingUser = matchingUser;
    }

    /**
     * 스터디 매칭화면을 출력하는 메소드
     */
    @Override
    public void info() {

        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("------------------------------⋆⁺₊⋆ 📖 ⋆⁺₊⋆-------------------------------");
            System.out.println("                           스터디 매칭 추가입력");
            System.out.println("----------------------------------------------------------------------");
            System.out.println();
            System.out.println("                             1. 매칭하기 📖");
            System.out.println("                             0. 뒤로가기 ↩️");
            System.out.println();
            System.out.println("----------------------------------------------------------------------");
            System.out.print("                             ▶ 메뉴 선택: ");

            String sel = scan.nextLine();

            switch (sel) {
                case "1":
                    if (Data.isMatchingListEmpty()) {
                        break;
                    }
                    add();
                    break;

                case "0":
                    System.out.println("이전 화면으로 돌아갑니다..");
                    return;

                default:
                    System.out.println("잘못된 숫자를 입력받았습니다.");
                    Data.pause();
                    break;
            }

        }

    }

    /**
     * 회원의 학점과 공부 분야를 저장하는 메소드
     */
    @Override
    public boolean add() {

        MatchingResultInterface matchingresultinterface = new MatchingResultInterface();
        matchingresultinterface.begin(matchingUser.getGrade(), matchingUser.getStudy());

        return false;
    }

}