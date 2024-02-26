package com.project.ssm.board;

import java.util.Scanner;

import com.project.ssm.data.Data;

public class InquiryBoard {
	
	public void mainInterface() {
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println();
			System.out.println("========================================");
			System.out.println("             문의 게시판");
			System.out.println("========================================");
			
			System.out.println();
		    System.out.println("———————————————————————————————————");
	        System.out.println("|      1. 글쓰기      |     2. 글보기   |");
	        System.out.println("|——————————————————|——————————————|");
	        System.out.println("|                0. 뒤로가기            |");
	        System.out.println("|——————————————————————————————————|");
	        System.out.println();
	        
			System.out.print("메뉴 선택 : ");
			String select = scan.nextLine();
			
			if(select.equals("1")) {
				
				String boardCategory = "문의게시판";
				
				BoardService boardService = new BoardService();
				boardService.creteBoard(boardCategory);
				
			}else if(select.equals("2")) {
				
				inquiryBoardList();
				
			}else if(select.equals("0")){
				
				loop = false;
				
			}
		}
		
	}

	
	public void inquiryBoardList() {
		
		System.out.println();
		
		String boardType = "inquiryBoard";
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while(loop) {
		
			System.out.println("[번호]\t[제목]\t\t [작성자]\t[날짜/시간]");
			System.out.println("----------------------------------------------------------");
			
			for(String inquiryList : Data.inquiryBoard) {
				String[] inquiry = inquiryList.split(",");
				
				System.out.printf("%5s\t%5s\t%5s\t%s\n"
									, inquiry[0]
									, inquiry[1]
									, inquiry[4]
									, inquiry[3]);
				
			}
			
			System.out.println();
			System.out.print("글 번호 선택(0.뒤로가기) : ");
			String select = scan.nextLine();
			
			if(!(select.equals("0"))) {
				BoardService read = new BoardService();
				read.boardView(select, boardType);
			}else if(select.equals("0")) {
				loop = false;
			}
		
		}
		
	}

}
