package com.project.ssm.matching;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.project.ssm.data.Data;


/**
 * 연애 매칭 클래스입니다.
 *
 * @author 김경현, 김유진
 */
public class LoveMatch implements Matching {

    private final MatchingUser matchingUser;

    private final Scanner scanner = new Scanner(System.in);

    private int minHeight;
    private int maxHeight;
    private int minWeight;
    private int maxWeight;

    public LoveMatch(MatchingUser matchingUser) {
        this.matchingUser = matchingUser;
    }

    /**
     * 연애 매칭 화면을 출력하는 메소드
     *
     * @author 김경현, 김유진
     */
    @Override
    public void info() {

        while (true) {

            System.out.println();
            System.out.println("------------------------------⋆⁺₊⋆ 💗 ⋆⁺₊⋆-------------------------------");
            System.out.println("                            연애 매칭 추가입력");
            System.out.println("----------------------------------------------------------------------");
            System.out.println();
            System.out.println("                             1. 매칭하기 💘");
            System.out.println("                             0. 뒤로가기 ↩️");
            System.out.println();
            System.out.println("----------------------------------------------------------------------");
            System.out.print("                             ▶ 메뉴 선택: ");

            switch (scanner.nextLine()) {
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
                    System.out.println("🚨 잘못된 번호를 입력했습니다.");
                    Data.pause();
                    break;
            }

        }

    }


    /**
     * 저장된 연애 매칭정보를 삭제하는 메소드
     *
     * @author 김경현, 김유진
     */

    @Override
    public boolean add() {

        boolean validInput = false;

        while (!validInput) {

            try {
                System.out.println("----------------------------------------------------------------------");
                System.out.println("※ 상대의 원하는 조건을 입력해주세요.");
                System.out.println("☞ 키: 130~200(cm) ㅣ 몸무게: 30~90(kg)");
                System.out.print("▶ 최소 키(cm): ");
                minHeight = MatchingUserProfile.checkHeight(scanner.nextLine());

                System.out.print("▶ 최대 키(cm): ");
                maxHeight = MatchingUserProfile.checkHeight(scanner.nextLine());
                checkMaxGreaterThanMin(minHeight, maxHeight);

                System.out.print("▶ 최소 몸무게(kg): ");
                minWeight = MatchingUserProfile.checkWeight(scanner.nextLine());

                System.out.print("▶ 최대 몸무게(kg): ");
                maxWeight = MatchingUserProfile.checkWeight(scanner.nextLine());
                checkMaxGreaterThanMin(minWeight, maxWeight);
                System.out.println("----------------------------------------------------------------------");

                validInput = true;

            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자만 입력이 가능합니다.");

            } catch (IllegalArgumentException e) {
                System.out.println("❌ 입력한 값을 다시 확인해주세요.");
            }

        }

        List<MatchingUser> loveUserList = new ArrayList<>();

        filterAndAddMatchingUsers(loveUserList);

        if (loveUserList.isEmpty()) {
            System.out.println("조건에 맞는 상대를 찾지 못했습니다.");
            return false;
        }

        System.out.print("♥️ 매칭이 완료되었습니다! ♥️");
        Data.pause();

        MatchingUser otherUser = loveUserList.get(getRandomValue(loveUserList));

        showLoveMatch(loveUserList, otherUser);

        System.out.println("상대방에게 매칭 알람을 보내시겠습니까?");
        System.out.print("입력(Y/N): ");

        String answer = scanner.nextLine().toUpperCase();

        if (answer.equals("Y")) {
            Data.matchingResultUserListAdd(matchingUser, otherUser, Category.Love.getName());
            System.out.println("알람을 보냈습니다.");
            Data.pause();
        } else {
            System.out.println();
            System.out.println("취소했습니다.");
            Data.pause();
        }

        return true;
    }


    private void filterAndAddMatchingUsers(List<MatchingUser> loveUserList) {
        for (MatchingUser user : Data.matchingUserList) {

            if (isSameGender(user)) continue;

            // cc 불가능이면 같은 학과는 제외
            if (matchingUser.getCc().equals("N")) {
                if (isSameMajor(user)) continue;
            }
            if (isValidHeight(user)) continue;

            if (isValidWeight(user)) continue;

            loveUserList.add(user);
        }
    }

    private void showLoveMatch(List<MatchingUser> loveUserList, MatchingUser otherUser) {
        System.out
                .println("--------------------------------⋆⁺₊⋆ 💗 ⋆⁺₊⋆----------------------------------");

        System.out.println();
        System.out.printf("                💗 원하는 조건의 %d명의 이성 중 1명을 매칭했습니다 💗\n", loveUserList.size());
        System.out.println();
        System.out.println("                              [나의 Info..]");
        System.out.println();
        System.out.printf("이름: %sㅣ나이: %dㅣ연락처: %sㅣ성별: %sㅣ전공: %sㅣ키: %d|몸무게: %d\n"
                , matchingUser.getName()
                , matchingUser.getAge()
                , matchingUser.getTel()
                , matchingUser.getGender()
                , matchingUser.getMajor()
                , matchingUser.getHeight()
                , matchingUser.getWeight());


        System.out.println("                                        \r\n"
                + "                              /////   /////        \r\n"
                + "                            /////////////////      \r\n"
                + "                            /////////////////      \r\n"
                + "                             ///////////////       \r\n"
                + "                               ///////////         \r\n"
                + "                                  /////            \r\n"
                + "                                    /              ");
        System.out.println();
        System.out.println("                              [그대의 Info..]");
        System.out.println();
        System.out.printf("이름: %sㅣ나이: %dㅣ연락처: %sㅣ성별: %sㅣ전공: %sㅣ키: %d|몸무게: %d\n"
                , otherUser.getName()
                , otherUser.getAge()
                , otherUser.getTel()
                , otherUser.getGender()
                , otherUser.getMajor()
                , otherUser.getHeight()
                , otherUser.getWeight());

        System.out.println();
        System.out.println("--------------------------------⋆⁺₊⋆ 💗 ⋆⁺₊⋆----------------------------------");
        System.out.println();
    }

    private boolean isValidWeight(MatchingUser user) {
        return user.getWeight() < minWeight || user.getWeight() > maxWeight;
    }

    private boolean isValidHeight(MatchingUser user) {
        return user.getHeight() < minHeight || user.getHeight() > maxHeight;
    }

    private boolean isSameMajor(MatchingUser user) {
        // 같은 학과 제외
        return user.getMajor().equals(matchingUser.getMajor());
    }

    private boolean isSameGender(MatchingUser user) {
        return matchingUser.getGender().equals(user.getGender());
    }

    private int getRandomValue(List<MatchingUser> loveUserList) {

        Random random = new Random();

        while (true) {

            int randomValue = random.nextInt(loveUserList.size() - 1);

            if (!isEqualToRandomInstance(randomValue)) {
                return randomValue;
            }

        }
    }

    private boolean isEqualToRandomInstance(int randomValue) {
        MatchingUser user = Data.matchingUserList.get(randomValue);
        return user == matchingUser;
    }


    private void checkMaxGreaterThanMin(int minValue, int maxValue) {

        if (maxValue < minValue) {
            throw new IllegalArgumentException("최댓값은 최솟값보다 커야합니다.");
        }

    }

}
