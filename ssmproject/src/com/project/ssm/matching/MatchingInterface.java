package com.project.ssm.matching;

import java.util.Scanner;
import com.project.ssm.data.Data;

/**
 * 매칭 화면 메인 인터페이스 클래스입니다.
 * @author 김경현, 김유진
 */
public class MatchingInterface {

	private Matching matching;

	/**
	 * 매칭 메인 화면을 출력하는 메소드
	 */
	public void begin() {
		
		Scanner scan = new Scanner(System.in);

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

            switch (scan.nextLine()) {
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

}

	
		
	

