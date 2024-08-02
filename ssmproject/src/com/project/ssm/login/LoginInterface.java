package com.project.ssm.login;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.ssm.board.BoardInterface;
import com.project.ssm.data.Data;
import com.project.ssm.matching.MatchingInterface;
import com.project.ssm.matching.MatchingResultUser;
import com.project.ssm.matching.MatchingUser;
import com.project.ssm.rental.RentalInterface;
import com.project.ssm.user.User;

public class LoginInterface {

	public void loginMenu() {

		String name = "";

		Scanner scan = new Scanner(System.in);

		for (User user : Data.userList) {

			if (LoginService.finalId.equals(user.getId())) {
				name = user.getName();
			}
		}

		boolean loop = true;

		while (loop) {

			System.out.println();
			System.out.println("----------------------------------------");
			System.out.println();
			System.out.printf("           [%s]님 환영합니다.\n", name);
			System.out.println();
			System.out.println("----------------------------------------");
			System.out.println();
			System.out.println("             * 매칭 알림 *");
			matchingAlarm();
			System.out.println("⡇⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⡇");
			System.out.println("⡇               공지사항   	        ⡇");
			System.out.println("⡇                      		        ⡇");
			System.out.println("⡇⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⡇");
			System.out.println("⡇                        	        ⡇");
			System.out.print("⡇	     ");
			schedule();
			System.out.println("		⡇");
			System.out.println("⡇                      		        ⡇");
			System.out.println("⡇                         	        ⡇");
			System.out.println("⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉");

			System.out.println();
			System.out.println("⡇⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⡇");
			System.out.println("⡇    1. 게시판      ⡇    2. 매칭	   ⡇");
			System.out.println("⡇⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⡇");
			System.out.println("⡇    3. 시설 대여   ⡇    4. My Page   ⡇");
			System.out.println("⡇⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⡇");
			System.out.println("⡇            0. 로그아웃              ⡇");
			System.out.println("⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉");

			System.out.println();
			System.out.print("메뉴 선택: ");

			String sel = scan.nextLine();

			if (sel.equals("1")) {

				// 1. 게시판
				BoardInterface boardInterface = new BoardInterface();
				boardInterface.boardMain();

			} else if (sel.equals("2")) {
				// 2. 매칭
				MatchingInterface matching = new MatchingInterface();
				matching.begin();

			} else if (sel.equals("3")) {

				// 3. 시설 대여
				RentalInterface rentalInterface = new RentalInterface();
				rentalInterface.Rental();

			} else if (sel.equals("4")) {

				// 4. My Page
				MyPageService myPageService = new MyPageService();
				myPageService.myPage();

			} else if (sel.equals("0")) {

				// 5. 로그아웃
				// 로그아웃을 누르면 메인화면으로 돌아감.
				loop = false;

			} else {

				// 0. 종료
				loop = false;
			}
		} // while
	}// loginMenu()

	private void matchingAlarm() {

		boolean check = false;
		// 10,1,양하은,24,의예과,여자,20176870,최나형,24,유아교육과,남자,운동
		for (int i = Data.matchingResultUserList.size() - 1; i >= 0; i--) {
			if (Data.matchingResultUserList.get(i).getOtherId().equals(LoginService.finalId)) {
				System.out.println();
				System.out.println("\t     🚨" + Data.matchingResultUserList.get(i).getCategory() + "🚨");
				System.out.println();
				String tel = "";
				for (User u : Data.userList) {
					if (u.getId().equals(Data.matchingResultUserList.get(i).getId())) {
						tel = u.getTel();
					}
				}
				System.out.println("[이름]\t[나이]\t[성별]\t[전화번호]");
				System.out.println();
				System.out.printf("%s%6s%6s  %15s", Data.matchingResultUserList.get(i).getName(),
						Data.matchingResultUserList.get(i).getAge(), Data.matchingResultUserList.get(i).getGender(),
						tel);
				System.out.println();
				System.out.println();

				check = true;
			}
		}

		if (!check) {
			System.out.println();
			System.out.println("----------------------------------------");
			System.out.println("          매칭 알람이 없습니다.");
			System.out.println("----------------------------------------");
			System.out.println();
		}

	}

	private void schedule() {

		ArrayList<String> scheduleTime = new ArrayList<String>();

		Calendar now = Calendar.getInstance();

		Calendar scheduleCal = Calendar.getInstance();

		for (int i = 0; i < Data.scheduleList.size(); i++) {

			String[] temp = Data.scheduleList.get(i).split(",");

			String[] day = temp[0].split("/");

			int month = Integer.parseInt(day[0]);
			int date = Integer.parseInt(day[1]);

			scheduleCal.set(2022, month, date);

			if (Integer.parseInt(day[0]) == now.get(Calendar.MONTH) + 1
					&& Integer.parseInt(day[1]) < now.get(Calendar.DATE)) {

				Data.scheduleList.remove(Data.scheduleList.get(i));

			} else if (Integer.parseInt(day[0]) < now.get(Calendar.MONTH) + 1) {

				Data.scheduleList.remove(Data.scheduleList.get(i));

			} else {

				scheduleTime.add(Data.scheduleList.get(i));

			}

		}

		String[] temp = scheduleTime.get(0).split(",");

		if (temp[0].equals(temp[1])) {
			String[] temp2 = temp[0].split("/");

			System.out.print(temp2[0] + "월 " + temp2[1] + "일 " + "*" + temp[2] + "*");

		}

	}

	private void logOut() {

	}

}
