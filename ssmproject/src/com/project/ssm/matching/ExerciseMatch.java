package com.project.ssm.matching;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginInterface;
import com.project.ssm.login.LoginService;
import com.project.ssm.user.User;

import java.util.Scanner;

/**
 * 운동 매칭 클래스입니다.
 *
 * @author 김경현, 김유진
 */
public class ExerciseMatch implements Matching {

    /**
     * 운동 매칭화면을 출력하는 메소드
     */
    @Override
    public void info(MatchingUser matchingUser) {

        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("------------------------------⋆⁺₊⋆ 💪 ⋆⁺₊⋆-------------------------------");
            System.out.println("                            운동 매칭 추가입력");
            System.out.println("----------------------------------------------------------------------");
            System.out.println();
            System.out.println("                             1. 매칭하기 💪");
            System.out.println("                             0. 뒤로가기 ↩️");
            System.out.println();
            System.out.println("----------------------------------------------------------------------");
            System.out.print("                             ▶ 메뉴 선택: ");

            switch (scan.nextLine()) {
                case "1":
                    add(matchingUser);
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
     * 원하는 운동 분야를 저장하는 메소드
     */
    @Override
    public void add(MatchingUser matchingUser) {

        MatchingResultInterface matchInfo = new MatchingResultInterface();
        matchInfo.begin(matchingUser.getExercise());

    }


}
