package com.project.ssm.matching;

import java.util.Scanner;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginInterface;
import com.project.ssm.login.LoginService;

/**
 * 매칭 화면 메인 인터페이스 클래스입니다.
 *
 * @author 김경현, 김유진
 */
public class MatchingInterface {

    private Match match;
    private MatchingUser matchingUser;

    /**
     * 매칭 메인 화면을 출력하는 메소드
     */
    public void begin() {

        Scanner scanner = new Scanner(System.in);

        //matchingList에 등록된 정보가 있는지 검색
        if (!isPreviousMatch()) {
            System.out.print("매칭기록이 없습니다. 매칭에 필요한 정보를 입력하기 위한 화면으로 이동하시겠습니까?(Y/N): ");
            if (scanner.nextLine().equalsIgnoreCase("Y")) {

                MatchingUserProfile matchingUserProfile = new MatchingUserProfile();
                matchingUser = matchingUserProfile.begin(); // 매칭에 필요한 정보들을 받아 matchingUser 인스턴스에 저장
                Data.matchingUserList.add(matchingUser); // 리스트에 추가 후 파일에 기록

                Data.save();
                System.out.println("저장이 완료됐습니다!");
                Data.pause();

            } else {
                System.out.print("메인 화면으로 돌아갑니다. ");
                Data.pause();
                return;
            }
        }

        while (true) {

            System.out.println();
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
            System.out.println("                                 매칭");
            System.out.println("---------------------------------------------------------------------");
            System.out.println();
            System.out.println("                             1. 운동 💪");
            System.out.println("                             2. 스터디 📖");
            System.out.println("                             3. 연애 💘");
            System.out.println("                             4. 삭제하기 🗑︎");
            System.out.println("                             5. 수정하기 ︎");
            System.out.println("                             0. 뒤로가기 ↩️");
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
            System.out.print("                             ▶ 메뉴 선택: ");

            switch (scanner.nextLine()) {
                case "1":
                    // 1. 운동추가정보
                    match = new Match(new ExerciseMatch(matchingUser));
                    match.start(matchingUser);
                    break;

                case "2":
                    // 2. 스터디추가정보
                    match = new Match(new StudyMatch(matchingUser));
                    match.start(matchingUser);
                    break;

                case "3":
                    // 3. 연애추가정보
                    match = new Match(new LoveMatch(matchingUser));
                    match.start(matchingUser);
                    break;

                case "4":
                    // 4. 삭제
                    delete(matchingUser);
                    break;

                case "5":
                    // 5. 수정
                    if (update(matchingUser)) {
                        Data.save();
                        System.out.println("수정이 성공적으로 완료됐습니다!");
                    }
                    break;

                case "0":
                    // 0. 뒤로가기
                    return;

                default:
                    System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
                    Data.pause();
                    break;
            }

        }

    }

    private boolean update(MatchingUser matchingUser) {

        Scanner scanner = new Scanner(System.in);

        boolean validInput = false;

        while (!validInput) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println(matchingUser);
            System.out.println("---------------------------------------------------------------------");
            System.out.println("※ 수정할 번호를 입력해주세요. 0을 입력시 이전 메뉴로 돌아갑니다.");
            System.out.print("▶ 번호 선택: ");

            String sel = scanner.nextLine();

            try {
                switch (sel) {
                    case "1":
                        System.out.print("▶ 키(cm): ");
                        int height = MatchingUserProfile.checkHeight(scanner.nextLine());
                        matchingUser.setHeight(height);
                        break;

                    case "2":
                        System.out.print("▶ 몸무게(kg): ");
                        int weight = MatchingUserProfile.checkWeight(scanner.nextLine());
                        matchingUser.setWeight(weight);
                        break;

                    case "3":
                        System.out.print("▶ 과CC 가능여부(Y/N):");
                        String cc = MatchingUserProfile.checkCC(scanner.nextLine().toUpperCase());
                        matchingUser.setCc(cc);
                        break;

                    case "4":
                        MatchingUserProfile.showSportsCategories();
                        System.out.print("▶ 운동 분야 번호: ");
                        String exercise = MatchingUserProfile.checkExercise(scanner.nextLine());
                        matchingUser.setExercise(exercise);
                        break;

                    case "5":
                        System.out.print("▶ 학점 (1.0 ~ 4.5): ");
                        Double grade = MatchingUserProfile.checkGrade(scanner.nextLine());
                        matchingUser.setGrade(grade);
                        break;

                    case "6":
                        MatchingUserProfile.showStudyCategories();
                        System.out.print("▶ 공부 분야: ");
                        String study = MatchingUserProfile.checkStudy(scanner.nextLine());
                        matchingUser.setStudy(study);
                        break;

                    case "0":
                        System.out.println("이전 화면으로 돌아갑니다..");
                        break;

                    default:
                        System.out.println("잘못된 숫자를 입력받았습니다.");
                        Data.pause();
                        break;
                }

                validInput = true;

            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력이 가능합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println("입력한 값을 다시 확인해주세요.");
            }
        }

        return false;
    }

    private boolean isPreviousMatch() {
        for (MatchingUser matchingUser : Data.matchingUserList) {
            if (matchingUser.getId().equals(LoginService.finalId)) {
                this.matchingUser = matchingUser;
                return true;
            }
        }
        return false;
    }

    public void delete(MatchingUser matchingUser) {

        // 추가 정보 삭제
        System.out.println();
        Scanner scan = new Scanner(System.in);

        System.out.print("🚨 삭제 시 입력한 추가 정보가 모두 사라집니다. 진행하시겠습니까? (Y/N): ");

        String sel = scan.nextLine().toUpperCase();

        if (sel.equals("Y")) {

            Data.matchingUserList.remove(matchingUser);

            Data.save();

            System.out.println("메인 화면으로 돌아갑니다.");
            Data.pause();

            LoginInterface loginInterface = new LoginInterface();
            loginInterface.loginMenu();


        } else if (sel.equals("N")) {
            System.out.println("매칭 추가입력 화면으로 돌아갑니다..");
            Data.pause();

        } else {
            System.out.println("🚨 잘못된 문자를 입력했습니다.");
            Data.pause();
        }

    }


}

	
		
	

