package com.project.ssm.matching;

import java.util.Scanner;
import com.project.ssm.data.Data;
import com.project.ssm.login.LoginService;

/**
 * 매칭 화면 메인 인터페이스 클래스입니다.
 * @author 김경현, 김유진
 */
public class MatchingInterface {

	private Matching matching;
    private MatchingUser matchingUser;

    /**
	 * 매칭 메인 화면을 출력하는 메소드
	 */
	public void begin() {

		Scanner scanner = new Scanner(System.in);
        
        //matchingList에 등록된 정보가 있는지 검색
        if (!isPreviousMatch()) {
            System.out.print("매칭기록이 없습니다. 매칭에 필요한 정보를 입력하기 위한 화면으로 이동하시겠습니까?(Y/N): ");
            if (scanner.nextLine().toUpperCase().equals("Y")) {

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
			System.out.println("                             0. 뒤로가기 ↩️");
			System.out.println();
			System.out.println("---------------------------------------------------------------------");
			System.out.print("                             ▶ 메뉴 선택: ");

            switch (scanner.nextLine()) {
                case "1":
                    // 1. 운동추가정보
                    matching = new ExerciseMatch();
                    matching.info();
                    break;

                case "2":
                    // 2. 스터디추가정보
                    matching = new StudyMatch();
                    matching.info();
                    break;

                case "3":
                    // 3. 연애추가정보
                    matching = new LoveMatch();
                    matching.info();
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

    private boolean isPreviousMatch() {
        for (MatchingUser matchingUser : Data.matchingUserList) {
            if (matchingUser.getId().equals(LoginService.finalId)) {
                this.matchingUser = matchingUser;
                return true;
            }
        }
        return false;
    }

}

	
		
	

