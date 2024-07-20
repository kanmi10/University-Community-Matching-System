package com.project.ssm.matching;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginService;
import com.project.ssm.user.User;

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

        String exercise;
        double grade;
        String study;

        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("※ 자신의 신체치수와 과CC 가능여부를 입력해주세요.");
        System.out.println();

        try {
            System.out.print("▶ 키(cm): ");
            int height = parseHeight(scanner.nextLine());

            System.out.print("▶ 몸무게(kg): ");
            int weight = parseWeight(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력이 가능합니다.");

        } catch (IllegalArgumentException e) {
            System.out.println("정확한 입력을 해주세요.");
        }


        newMatchingUser.setHeight(scanner.nextInt());
        scanner.nextLine();


        newMatchingUser.setWeight(scanner.nextInt());
        scanner.nextLine();

        System.out.print("과CC 가능여부(Y/N):");
        newMatchingUser.setCc(scanner.nextLine().toUpperCase());
        System.out.println();
        System.out.println("---------------------------------------------------------------------");

        return newMatchingUser;
    }

    private int parseHeight(String height) {
        int parseHeight = Integer.parseInt(height);
        if (parseHeight <= 0) {
            throw new IllegalArgumentException("키는 양수로 입력해주십시오.");
        }
        if (parseHeight < 130 || parseHeight > 200) {
            throw new IllegalArgumentException("❌ 키는 130~200(cm) 사이로 입력해주세요.");
        }

        return parseHeight;
    }

    private int parseWeight(String weight) {
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
