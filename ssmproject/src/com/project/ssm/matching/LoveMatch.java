package com.project.ssm.matching;

import java.util.Scanner;

import com.project.ssm.data.Data;


/**
 * 연애 매칭 클래스입니다.
 *
 * @author 김경현, 김유진
 */
public class LoveMatch implements Matching {

    private final MatchingUser matchingUser;

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

        Scanner scan = new Scanner(System.in);

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

            String sel = scan.nextLine();

            switch (sel) {
                case "1":
                    add();
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
        Scanner scanner = new Scanner(System.in);

        boolean validInput = false;

        while (!validInput) {

            try {
                System.out.println("----------------------------------------------------------------------");
                System.out.println("※ 상대의 원하는 조건을 입력해주세요.");
                System.out.println("☞ 키: 130~200(cm) ㅣ 몸무게: 30~90(kg)");
                System.out.print("▶ 최소 키(cm): ");
                int minHeight = MatchingUserProfile.checkHeight(scanner.nextLine());

                System.out.print("▶ 최대 키(cm): ");
                int maxHeight = MatchingUserProfile.checkHeight(scanner.nextLine());
                checkMaxGreaterThanMin(minHeight, maxHeight);

                System.out.print("▶ 최소 몸무게(kg): ");
                int minWeight = MatchingUserProfile.checkWeight(scanner.nextLine());

                System.out.print("▶ 최대 몸무게(kg): ");
                int maxWeight = MatchingUserProfile.checkWeight(scanner.nextLine());
                checkMaxGreaterThanMin(minWeight, maxWeight);
                System.out.println("----------------------------------------------------------------------");

                // 매칭결과 인터페이스로 이동
                System.out.print("♥️ 매칭이 완료되었습니다! ♥️");
                Data.pause();

                MatchingResultInterface matchingResultInterface = new MatchingResultInterface();
                matchingResultInterface.begin(matchingUser.getCc(),
                        String.valueOf(minHeight),
                        String.valueOf(maxHeight),
                        String.valueOf(minWeight),
                        String.valueOf(maxWeight));

                validInput = true;

            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자만 입력이 가능합니다.");

            } catch (IllegalArgumentException e) {
                System.out.println("❌ 입력한 값을 다시 확인해주세요.");
            }

        }

        return false;

    }

    private void checkMaxGreaterThanMin(int minValue, int maxValue) {

        if (maxValue < minValue) {
            throw new IllegalArgumentException("최댓값은 최솟값보다 커야합니다.");
        }

    }

}
