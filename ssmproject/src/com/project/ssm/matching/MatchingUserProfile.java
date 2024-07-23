package com.project.ssm.matching;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginService;
import com.project.ssm.user.User;

import javax.swing.text.Style;
import java.util.Scanner;

public class MatchingUserProfile {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * private String id;		// ID(학번)
     * private String name;	    // 이름
     * private int age;		    // 나이
     * private String major;	// 전공
     * private String gender;	// 성별
     * private int height;		// 키
     * private int weight;		// 몸무게
     * private String cc;		// 과CC 가능여부
     * private String exercise;// 운동종목
     * private double grade;	// 학점
     * private String study;	// 공부
     *
     * @return
     */
    public MatchingUser begin() {

        MatchingUser newMatchingUser = new MatchingUser();

        for (User user : Data.userList) {
            if (user.getId().equals(LoginService.finalId)) {
                newMatchingUser.setId(user.getId());
                newMatchingUser.setName(user.getName());
                newMatchingUser.setAge(Data.getAge(user.getJumin()));
                newMatchingUser.setMajor(user.getMajor());
                newMatchingUser.setGender(Data.getGender(user.getJumin()));
            }
        }

        boolean validInput = false;

        while (!validInput) {
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
            System.out.println("※ 자신의 신체치수와 과CC 가능여부를 입력해주세요.");

            try {
                System.out.print("▶ 키(cm): ");
                int height = checkHeight(scanner.nextLine());
                newMatchingUser.setHeight(height);

                System.out.print("▶ 몸무게(kg): ");
                int weight = checkWeight(scanner.nextLine());
                newMatchingUser.setWeight(weight);

                System.out.print("▶ 과CC 가능여부(Y/N):");
                String cc = checkCC(scanner.nextLine().toUpperCase());
                newMatchingUser.setCc(cc);

                showSportsCategories();
                System.out.print("▶ 운동 분야 번호: ");
                String exercise = checkExercise(scanner.nextLine());
                newMatchingUser.setExercise(exercise);

                showStudyCategories();
                System.out.print("▶ 학점 (1.0 ~ 4.5): ");
                Double grade = checkGrade(scanner.nextLine());
                newMatchingUser.setGrade(grade);

                System.out.print("▶ 공부 분야: ");
                String study = checkStudy(scanner.nextLine());
                newMatchingUser.setStudy(study);

                validInput = true;

            } catch (NumberFormatException e) {
                System.out.println("정확한 값을 입력해주세요");
            } catch (IllegalArgumentException e) {
                System.out.println("정확한 값을 입력해주세요.");
            }

            System.out.println("---------------------------------------------------------------------");

        }
        return newMatchingUser;
    }

    private String checkStudy(String studyNumber) {
        if (Integer.parseInt(studyNumber) > 0 && Integer.parseInt(studyNumber) < Study.values().length) {

            Study[] studies = Study.values();

            for (Study study : studies) {
                if (study.ordinal() == (Integer.parseInt(studyNumber) - 1)) {
                    return study.getName();
                }
            }
        }
        throw new IllegalArgumentException("❌번호를 다시 입력해주세요.");
    }

    private boolean checkNum(String number) {
        if (number == null && number.isEmpty()) {
            return false;
        }

        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Double checkGrade(String score) {

        if (!checkNum(score)) {
            throw new IllegalArgumentException("❌학점은 숫자로 입력해주세요.");
        }

        if (Double.parseDouble(score) >= 1.0 && Double.parseDouble(score) <= 4.5) {
            return Double.parseDouble(score);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private String checkExercise(String exerciseNumber) {

        if (Integer.parseInt(exerciseNumber) > 0 && Integer.parseInt(exerciseNumber) <= Exercise.values().length) {
            //사용자가 선택한 운동번호와 일치하는 운동을 구해 초기화
            String exercise = "";

            Exercise[] exercises = Exercise.values();
            for (Exercise sport : exercises) {
                if (sport.ordinal() == (Integer.parseInt(exerciseNumber) - 1)) {
                    exercise = sport.getName();
                }
            }

            return exercise;
        }
        throw new IllegalArgumentException("❌번호를 다시 입력해주세요.");
    }

    private String checkCC(String cc) {
        if (cc.equals("Y") || cc.equals("N")) {
            return cc;
        }
        throw new IllegalArgumentException("❌CC 가능여부는 (Y 또는 N)만 입력이 가능합니다.");
    }

    private static void showSportsCategories() {
        Exercise[] sports = Exercise.values();
        System.out.print("※ 운동 분야를 선택해주세요. [");
        for (int i = 0; i < sports.length; i++) {
            System.out.print((i + 1) + ". " + sports[i].getName() + sports[i].getEmoticon());
            if (i < sports.length - 1) {
                System.out.print(" ");
            }

        }
        System.out.println("]");
    }

    private void showStudyCategories() {
        System.out.print("※ 학점과 공부 분야를 선택해주세요. ");

        System.out.print("[");
        Study[] studies = Study.values();
        for (int i = 0; i < studies.length; i++) {
            System.out.print((i + 1) + ". " + studies[i].getName());
            if (i < studies.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println("]");
    }


    private int checkHeight(String height) {
        int parseHeight = Integer.parseInt(height);
        if (parseHeight <= 0) {
            throw new IllegalArgumentException("키는 양수로 입력해주십시오.");
        }
        if (parseHeight < 130 || parseHeight > 200) {
            throw new IllegalArgumentException("❌ 키는 130~200(cm) 사이로 입력해주세요.");
        }

        return parseHeight;
    }

    private int checkWeight(String weight) {
        int parseWeight = Integer.parseInt(weight);
        if (parseWeight <= 0) {
            throw new IllegalArgumentException("몸무게는 양수로 입력해주십시오.");
        }
        if (parseWeight < 30 || parseWeight > 90) {
            throw new IllegalArgumentException("❌ 몸무게는 30~90(kg) 사이로 입력해주세요.");
        }

        return parseWeight;
    }

}
