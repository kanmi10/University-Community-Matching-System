package com.project.ssm.findidpw;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.ssm.data.Data;
import com.project.ssm.user.User;

public class FindPw {

	public void myFindPw() {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println();
			System.out.println("========================");
			System.out.println("        비밀번호 찾기");
			System.out.println("========================");

			
			System.out.print("ID(학번): ");
			String id = scan.nextLine();
			
			System.out.print("주민등록번호: ");
			String jumin = scan.nextLine();
			
			ArrayList<User> ulist = Data.searchUserPw(id, jumin);
			
			if(ulist.size() != 0) {
				for(User user : ulist) {
						
						System.out.printf("찾으신 비밀번호는 <%s> 입니다.\n", user.getPw());
						loop = false;
				}
				
			} else {
				System.out.println("비밀번호를 찾을 수 없습니다.");
				loop = false;
			}
			
			Data.pause();
		}
		
	}//myFindPw

}
