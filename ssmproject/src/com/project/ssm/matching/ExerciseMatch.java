package com.project.ssm.matching;

import com.project.ssm.data.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 운동 매칭 클래스입니다.
 *
 * @author 김경현, 김유진
 */
public class ExerciseMatch extends Matching {

    private final MatchingUser matchingUser;

    private final Scanner scanner = new Scanner(System.in);

    public ExerciseMatch(MatchingUser matchingUser) {
        super(Category.Exercise.getName(), "💪");
        this.matchingUser = matchingUser;
    }


    @Override
    public List<MatchingUser> findMatches() {
        return filterAndAddMatchingUsers();
    }

    @Override
    public List<MatchingUser> filterAndAddMatchingUsers() {

        List<MatchingUser> exerciseUserList = new ArrayList<>();

        for (MatchingUser user : Data.matchingUserList) {

            if (!user.getExercise().equals(matchingUser.getExercise())) continue;

            exerciseUserList.add(user);
        }

        return exerciseUserList;
    }

    @Override
    public void showMatch(List<MatchingUser> exerciseList, MatchingUser otherUser) {

        System.out.println("matchingUser.getId() = " + matchingUser.getId());
        System.out.println("matchingUser.getPw() = " + matchingUser.getPw());
        System.out.println("matchingUser.getTel() = " + matchingUser.getTel());
        System.out.println("--------------------------------⋆⁺₊⋆ 💪 ⋆⁺₊⋆----------------------------------");

        System.out.println();
        System.out.printf("                💪 원하는 조건의 %d명의 회원 중 1명을 매칭했습니다 💪\n", exerciseList.size());
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

    @Override
    public void saveMatchData(MatchingUser otherUser) {
        System.out.println("매칭을 저장하시겠습니까?");
        System.out.print("입력(Y/N): ");
        String answer = scanner.nextLine().toUpperCase();

        if (answer.equals("Y")) {
            Data.matchingResultUserListAdd(matchingUser, otherUser, Category.Exercise.getName());
            Data.save();
            System.out.println("매칭 결과가 저장됐습니다!");
            Data.pause();
        } else {
            System.out.println();
            System.out.println("매칭을 취소했습니다.");
            Data.pause();
        }
    }


}
