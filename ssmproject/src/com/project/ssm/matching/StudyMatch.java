package com.project.ssm.matching;

import java.util.ArrayList;
import java.util.List;
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


    @Override
    public List<MatchingUser> findMatches() {

        return filterAndAddMatchingUsers();
    }

    @Override
    public List<MatchingUser> filterAndAddMatchingUsers() {

        List<MatchingUser> studyUserList = new ArrayList<>();

        for (MatchingUser user : Data.matchingUserList) {

            if (!user.getStudy().equals(matchingUser.getStudy())) continue;

            studyUserList.add(user);
        }

        return studyUserList;
    }

    @Override
    public void showMatch(List<MatchingUser> studyUserList, MatchingUser otherUser) {
        System.out.println("--------------------------------⋆⁺₊⋆ 📖 ⋆⁺₊⋆----------------------------------");
        System.out.println();
        System.out.printf("                📖 원하는 조건의 %d명의 회원 중 1명을 매칭했습니다 📖\n", studyUserList.size());
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

    @Override
    public void saveMatchData(MatchingUser otherUser) {
        System.out.println("매칭을 저장하시겠습니까?");
        System.out.print("입력(Y/N): ");
        String answer = scanner.nextLine().toUpperCase();

        if (answer.equals("Y")) {
            Data.matchingResultUserListAdd(matchingUser, otherUser, Category.Study.getName());
            Data.save();
            System.out.println("매칭 결과가 저장됐습니다!");
            Data.pause();
        } else {
            System.out.println();
            System.out.println("취소하였습니다.");
            Data.pause();
        }
    }


}