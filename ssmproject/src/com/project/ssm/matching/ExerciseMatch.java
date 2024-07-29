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
                    boolean result = add();
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

        // 매칭할 유저가 없다면 매칭 실패
        if (Data.matchingUserList.isEmpty()) {
            System.out.println("매칭에 실패했습니다. 잠시 후 다시 시도해주십시오.");
            return false;
        }

        int randomValue = getRandomValue();
        MatchingUser otherUser = Data.matchingUserList.get(randomValue);

        showExerciseMatch(otherUser);

        System.out.println("상대방에게 매칭 알람을 보내시겠습니까?");
        System.out.print("입력(Y/N): ");
        String answer = scanner.nextLine().toUpperCase();

        if (answer.equals("Y")) {
            matchingResultUserListAdd(otherUser);
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

    /**
 * 인수로 받은 MatchingUser 인스턴스, 카테고리로 matchingResultUser 리스트에 추가하는 메소드
 * @param otherUser
 */
public void matchingResultUserListAdd(MatchingUser otherUser) {

    int seq = 0;

    // 고유번호 부여 방식
    if (!Data.matchingResultUserList.isEmpty()) {
        seq = Data.matchingResultUserList.get(Data.matchingResultUserList.size() - 1).getMatchingNumber() + 1;
    } else {
        seq = 1;
    }

    // matchingResultUser 리스트에 저장
    MatchingResultUser resultUser = new MatchingResultUser();

    // 7,1,김형수,22,의예과,여자,18671707,이돈정,24,전자공학과,여자,연애
    resultUser.setMatchingNumber(seq);

    resultUser.setMyId(matchingUser.getId());
    resultUser.setMyName(matchingUser.getName());
    resultUser.setMyAge(matchingUser.getAge());
    resultUser.setMyMajor(matchingUser.getMajor());
    resultUser.setMyGender(matchingUser.getGender());

    resultUser.setOtherId(otherUser.getId());
    resultUser.setOtherName(otherUser.getName());
    resultUser.setOtherAge(otherUser.getAge());
    resultUser.setOtherMajor(otherUser.getMajor());
    resultUser.setOtherGender(otherUser.getGender());
    String category = "운동";
    resultUser.setCategory(category);

    Data.matchingResultUserList.add(resultUser);

}

    private int getRandomValue() {

        Random random = new Random();

        while (true) {

            int randomValue = random.nextInt(Data.matchingUserList.size() - 1);

            // 랜덤 인스턴스가 서로 같거나, 선호하는 운동 종목이 아니면 랜덤 정수 다시 구하기
            if (!isEqualToRandomInstance(randomValue) && !isEqualToExercise(randomValue)) {
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
