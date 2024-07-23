package com.project.ssm.matching;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginService;
import com.project.ssm.user.User;

import java.util.Scanner;

/**
 * 운동 매칭 클래스입니다.
 *
 * @author 김경현, 김유진
 */
public class ExerciseMatch implements Matching {

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
            System.out.println("                             1. 삭제하기 🗑︎");
            System.out.println("                             2. 매칭하기 💪");
            System.out.println("                             0. 뒤로가기 ↩️");
            System.out.println();
            System.out.println("----------------------------------------------------------------------");
            System.out.print("                             ▶ 메뉴 선택: ");

            switch (scan.nextLine()) {
                case "1":
                    delete();
                    break;
                case "2":
                    add();
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
    public void add() {

        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("----------------------------------------------------------------------");

            //운동 카테고리 출력
            showSportsCategories();

            System.out.print("▶ 운동 분야 번호: ");
            String selectedExercise = scan.nextLine();

            //사용자가 선택한 운동번호와 일치하는 운동을 구해 초기화
            String exercise = "";
            Exercise[] exercises = Exercise.values();
            for (Exercise sport : exercises) {
                if (sport.ordinal() == (Integer.parseInt(selectedExercise) - 1)) {
                    exercise = sport.getName();
                }
            }

            System.out.print("추가 정보를 저장하시겠습니까?(Y/N): ");
            String addInfoSave = scan.nextLine().toUpperCase();

            System.out.println("----------------------------------------------------------------------");


            if (addInfoSave.equals("Y")) {

                for (MatchingUser matchingUser : Data.matchingUserList) {

                    if (LoginService.finalId.equals(matchingUser.getId())) { // 이미 매칭 기록이 있는 사용자

                        matchingUser.setExercise(exercise);

                    }

                }

                //매칭 기록이 없는 사용자
				// 인스턴스 생성 후 리스트에 추가
                MatchingUser matchingUser = new MatchingUser();
                for (User user : Data.userList) { //user 리스트를 돌면서

                    if (user.getId().equals(LoginService.finalId)) {
                        matchingUser.setId(user.getId());
                        matchingUser.setName(user.getName());

						int age = Data.getAge(user.getJumin());

                        matchingUser.setAge(age);
                        matchingUser.setMajor(user.getMajor());

                        String gender = "";

                        if (user.getJumin().substring(7, 8).equals("1") || user.getJumin().substring(7, 8).equals("3")) {
                            gender = "남자";
                        } else if (user.getJumin().substring(7, 8).equals("2") || user.getJumin().substring(7, 8).equals("4")) {
                            gender = "여자";
                        }

                        matchingUser.setGender(gender);
                        //19679528,이정수,25,간호학과,남자,179,68,N,배드민턴,2.5,파이썬
                        matchingUser.setHeight(0);
                        matchingUser.setWeight(0);
                        matchingUser.setCc(null);
                        matchingUser.setExercise(exercise);
                        matchingUser.setGrade(0.0);
                        matchingUser.setStudy(null);


                        boolean check = true;

                        for (MatchingUser m : Data.matchingUserList) {

                            if (m.getId().equals(LoginService.finalId)) {
                                if (!m.getExercise().equals("null")) {

                                    System.out.println();
                                    System.out.println("등록된 정보로 매칭합니다.");
                                    check = false;
                                    Data.pause();
                                    break;
                                } else {

                                    m.setExercise(exercise);
                                    System.out.println("저장되었습니다..");
                                    Data.pause();
                                    check = false;
                                    break;

                                }
                            }
                        }

                        if (check) {
                            Data.matchingUserList.add(matchingUser);
                            System.out.println("저장되었습니다..");
                            break;
                        }
                        break;
                    }

                }
                // 매칭결과 인터페이스로 이동
                MatchingResultInterface matchingresultinterface = new MatchingResultInterface();
                matchingresultinterface.begin(exercise);
                return;

            } else if (addInfoSave.equals("N")) {
                System.out.println("매칭 추가입력 화면으로 돌아갑니다.");
                return;

            } else {
                System.out.println("🚨 잘못된 번호입니다.");
				System.out.println("매칭 추가입력 화면으로 돌아갑니다.");
                return;
            }
        }

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

    @Override
    public void delete() {

        // 추가 정보 삭제
        Scanner scan = new Scanner(System.in);

        System.out.print("🚨 삭제 시 입력한 추가 정보가 모두 사라집니다. 진행하시겠습니까? (Y/N): ");

        String sel = scan.nextLine();

        if (sel.toUpperCase().equals("Y")) {

            for (MatchingUser matchingUser : Data.matchingUserList) {

                // 데이터가 null이 아닐때 삭제
                if (LoginService.finalId.equals(matchingUser.getId()) && !(matchingUser.getExercise().equals("null"))) {

                    matchingUser.setExercise(null);

                    System.out.println("삭제가 완료됐습니다.");
                    Data.pause();
                    return;

                    // 데이터가 null일때
                } else if (LoginService.finalId.equals(matchingUser.getId()) && (matchingUser.getExercise().equals("null"))) {

                    System.out.println("삭제할 데이터가 존재하지 않습니다.");
                    Data.pause();
                    return;

                }

            }

            // N선택시 매칭 정보 추가 화면으로 이동
        } else if (sel.toUpperCase().equals("N")) {

            System.out.println("매칭 추가입력 화면으로 돌아갑니다..");
            Data.pause();

        } else {
            System.out.println("🚨 잘못된 문자를 입력했습니다.");
            Data.pause();
        }

    }

}
