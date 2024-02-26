package com.project.ssm.findidpw;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.ssm.data.Data;
import com.project.ssm.user.User;

public class FindId {

	public void myFindId() {
		
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println();
			System.out.println("==========================");
			System.out.println("        아이디 찾기");
			System.out.println("==========================");

			
			System.out.print("이름: ");
			String name = scan.nextLine();
			
			System.out.print("주민등록번호: ");
			String jumin = scan.nextLine();
			
			ArrayList<User> ulist = Data.searchUserId(name, jumin);
			
			if(ulist.size() != 0) {
				for(User user : ulist) {
						
						System.out.printf("찾으신 ID(학번)은 <%s> 입니다.\n", user.getId());
						loop = false;
				}
				
			} else {
				System.out.println("ID(학번)을 찾을 수 없습니다.");
				loop = false;
			}
			
			Data.pause();
		}
		
	}//myFindId

}
