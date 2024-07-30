package com.project.ssm.matching;

import com.project.ssm.data.Data;

import java.util.Scanner;

public class Match {

    private final Matching matching; // ExerciseMatch, LoveMatch, StudyMatch
    private final Scanner scanner = new Scanner(System.in);

    public Match(Matching matching) {
        this.matching = matching;
    }

    public void start(MatchingUser matchingUser) {

        while (true) {

            System.out.println();
            System.out.printf("-----------------------------⋆⁺₊⋆ %s ⋆⁺₊⋆-----------------------------\n", matching.getEmoticon());
            System.out.printf("                            %s 매칭 추가입력\n", matching.getName());
            System.out.println("----------------------------------------------------------------------");
            System.out.println();
            System.out.printf("                             1. 매칭하기 %s\n", matching.getEmoticon());
            System.out.println("                             0. 뒤로가기 ↩️");
            System.out.println();
            System.out.println("----------------------------------------------------------------------");
            System.out.print("                             ▶ 메뉴 선택: ");

            switch (scanner.nextLine()) {
                case "1":
                    if (Data.isMatchingListEmpty()) {
                        break;
                    }

                    if (!matching.add()) {
                        System.out.println("매칭에 실패했습니다.");
                    }
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





}
