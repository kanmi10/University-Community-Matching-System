package com.project.ssm.matching;

import java.util.Random;
import java.util.Scanner;

import com.project.ssm.data.Data;

/**
 * 스터디 매칭화면 클래스입니다.
 *
 * @author 김경현, 김유진
 */
public class StudyMatch extends Matching {

    private final MatchingUser matchingUser;

    private final Scanner scanner = new Scanner(System.in);

    public StudyMatch(MatchingUser matchingUser) {
        super(Category.Study.getName(), "📖");
        this.matchingUser = matchingUser;
    }

    /**
     * 스터디 매칭화면을 출력하는 메소드
     */
    @Override
    public void info() {

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
     * 회원의 학점과 공부 분야를 저장하는 메소드
     */
    @Override
    public boolean add() {

        MatchingUser otherUser = Data.matchingUserList.get(getRandomValue());

        if (otherUser == null) {
            System.out.println("조건에 맞는 상대를 찾지 못했습니다.");
            return false;
        }

        System.out.print("📖 매칭이 완료되었습니다! 📖");
        Data.pause();

        showStudyMatch(otherUser);

        System.out.println("상대방에게 매칭 알람을 보내시겠습니까?");
        System.out.print("입력(Y/N): ");
        String answer = scanner.nextLine().toUpperCase();

        if (answer.equals("Y")) {
            Data.matchingResultUserListAdd(matchingUser, otherUser, Category.Study.getName());
            System.out.println("알람을 보냈습니다.");
            Data.pause();
        } else {
            System.out.println();
            System.out.println("취소하였습니다.");
            Data.pause();
        }

        Data.save();
        System.out.println("저장을 완료했습니다!");

        return true;
    }

    private void showStudyMatch(MatchingUser otherUser) {
        System.out.println("--------------------------------⋆⁺₊⋆ 📖 ⋆⁺₊⋆----------------------------------");
        System.out.println();
        System.out.printf("                📖 원하는 조건의 %d명의 회원 중 1명을 매칭했습니다 📖\n", Data.matchingUserList.size());
        System.out.println();
        System.out.println("                              [나의 Info..]");
        System.out.println();

        System.out.printf("    이름: %sㅣ나이: %dㅣ연락처: %sㅣ성별: %sㅣ성적: %.1fㅣ공부유형: %s\n"
                , matchingUser.getName()
                , matchingUser.getAge()
                , matchingUser.getTel()
                , matchingUser.getGender()
                , matchingUser.getGrade()
                , matchingUser.getStudy());


        System.out.println("                    							\r\n"
                + "                                 ..////                   	\r\n"
                + "                          (///////    //..       		\r\n"
                + "                          (((//     ///////             \r\n"
                + "                           ((((//////////////..         \r\n"
                + "                             (((///////////////         \r\n"
                + "                              ((((///////.*.//,..       \r\n"
                + "                                ((#....,.#..            \r\n"
                + "                                  (#..#..                  ");

        System.out.println();
        System.out.println("                              [상대의 Info..]");

        System.out.println();
        System.out.printf("    이름: %sㅣ나이: %dㅣ연락처: %sㅣ성별: %sㅣ성적: %.1fㅣ공부유형: %s\n"
                , otherUser.getName()
                , otherUser.getAge()
                , otherUser.getTel()
                , otherUser.getGender()
                , otherUser.getGrade()
                , otherUser.getStudy());

        System.out.println();
        System.out.println("--------------------------------⋆⁺₊⋆ 📖 ⋆⁺₊⋆----------------------------------");
        System.out.println();
    }

    //TODO 모든 매칭 같은 메서드.. 리팩토링 필요
    private int getRandomValue() {

        Random random = new Random();

        while (true) {

            int randomValue = random.nextInt(Data.matchingUserList.size() - 1);

            // 랜덤 인스턴스가 서로 같거나, 선호하는 공부 종목 같지 않으면 랜덤 정수 다시 구하기
            if (!isEqualToRandomInstance(randomValue) && isEqualToStudy(randomValue)) {
                return randomValue;
            }

        }
    }


    private boolean isEqualToRandomInstance(int randomValue) {
        MatchingUser user = Data.matchingUserList.get(randomValue);
        return user == matchingUser;
    }

    private boolean isEqualToStudy(int randomValue) {
        MatchingUser user = Data.matchingUserList.get(randomValue);
        return user.getStudy().equals(matchingUser.getStudy());
    }

}