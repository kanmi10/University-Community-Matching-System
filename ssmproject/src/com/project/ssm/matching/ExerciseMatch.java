package com.project.ssm.matching;

import com.project.ssm.data.Data;

import java.util.Random;
import java.util.Scanner;

/**
 * 운동 매칭 클래스입니다.
 *
 * @author 김경현, 김유진
 */
public class ExerciseMatch implements Matching {

    private final MatchingUser matchingUser;

    private final Scanner scanner = new Scanner(System.in);

    public ExerciseMatch(MatchingUser matchingUser) {
        this.matchingUser = matchingUser;
    }

    /**
     * 운동 매칭화면을 출력하는 메소드
     */
    @Override
    public void info() {

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
                    if (Data.isMatchingListEmpty()) {
                        break;
                    }

                    if (!add()) {
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

    /**
     * 원하는 운동 분야를 저장하는 메소드
     */
    @Override
    public boolean add() {

        MatchingUser otherUser = Data.matchingUserList.get(getRandomValue());

        if (otherUser == null) {
            System.out.println("조건에 맞는 상대를 찾지 못했습니다.");
            return false;
        }

        System.out.print("💪 매칭이 완료되었습니다! 💪");
        Data.pause();

        showExerciseMatch(otherUser);

        System.out.println("상대방에게 매칭 알람을 보내시겠습니까?");
        System.out.print("입력(Y/N): ");
        String answer = scanner.nextLine().toUpperCase();

        if (answer.equals("Y")) {
            Data.matchingResultUserListAdd(matchingUser, otherUser, Category.Exercise.getName());
            System.out.println("알람을 보냈습니다.");
            Data.pause();
        } else {
            System.out.println();
            System.out.println("매칭을 취소하였습니다.");
            Data.pause();
        }

        Data.save();
        System.out.println("저장을 완료했습니다!");

        return true;
    }


    private void showExerciseMatch(MatchingUser otherUser) {
        System.out.println("--------------------------------⋆⁺₊⋆ 💪 ⋆⁺₊⋆----------------------------------");

        System.out.println();
        System.out.printf("                💪 원하시는 조건의 %d명의 회원 중 1명을 매칭했습니다 💪\n", Data.matchingUserList.size());
        System.out.println();
        System.out.println("                              [나의 Info..]");
        System.out.println();

        System.out.printf("         이름: %sㅣ나이: %dㅣ연락처: %sㅣ성별: %sㅣ운동: %s\n"
                , matchingUser.getName()
                , matchingUser.getAge()
                , matchingUser.getTel()
                , matchingUser.getGender()
                , matchingUser.getExercise());

        System.out.println();
        System.out.println("                       ,****                 ****       \r\n"
                + "                    ** ,****                 ****  **   \r\n"
                + "                    ** ,****                 ****  **   \r\n"
                + "                    ** ,**** %(           %% ****  **   \r\n"
                + "                  % ******** %# %%%%%%%%# %% ****  ** # \r\n"
                + "                    ** ,**** %(           %% ****  **   \r\n"
                + "                    ** ,****                 ****  **   \r\n"
                + "                    ** ,****                 ****  **   \r\n"
                + "                       ,****                 ****     ");
        System.out.println();
        System.out.println("                              [상대의 Info..]");
        System.out.println();
        System.out.printf("         이름: %sㅣ나이: %dㅣ연락처: %sㅣ성별: %sㅣ운동: %s\n"
                , otherUser.getName()
                , otherUser.getAge()
                , otherUser.getTel()
                , otherUser.getGender()
                , otherUser.getExercise());

        System.out.println();
        System.out.println("--------------------------------⋆⁺₊⋆ 💪 ⋆⁺₊⋆----------------------------------");
        System.out.println();
    }



    private int getRandomValue() {

        Random random = new Random();

        while (true) {

            int randomValue = random.nextInt(Data.matchingUserList.size() - 1);

            // 랜덤 인스턴스가 서로 같거나, 선호하는 운동 종목 같지 않으면 랜덤 정수 다시 구하기
            if (!isEqualToRandomInstance(randomValue) && isEqualToExercise(randomValue)) {
                return randomValue;
            }

        }
    }


    private boolean isEqualToRandomInstance(int randomValue) {
        MatchingUser user = Data.matchingUserList.get(randomValue);
        return user == matchingUser;
    }

    private boolean isEqualToExercise(int randomValue) {
        MatchingUser user = Data.matchingUserList.get(randomValue);
        return user.getExercise().equals(matchingUser.getExercise());
    }


}
