package com.project.ssm.board;

import java.util.Calendar;
import java.util.Scanner;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginInterface;
import com.project.ssm.login.LoginService;
import com.project.ssm.main.MainInterface;

public class BoardService {
	
	String id = LoginService.finalId;
	String name = Data.UserGetName();
	String title = "";
	String content = "";
	
	//게시판 글 생성
	public void creteBoard(String category) {
		
		Scanner scan = new Scanner(System.in);
		
		Calendar c = Calendar.getInstance();
		
		System.out.println();
		
		boolean loop = true;
		
		while(loop) {
		
			System.out.print("제목 : ");
			title = scan.nextLine();
			
			title = textValidation(title);
			
			if(title.length() > 49 || title.length() < 3) {
				System.out.println("--------------------------------------------");
				System.out.println("제목은 4자 이상, 15자 이내로 입력 해주세요.");
				System.out.println("--------------------------------------------");
				
			}else {
				loop = false;
			}
		
		}
		
		loop = true;
		
		while(loop) {
			System.out.print("내용 : ");
			content = scan.nextLine();
			
			content = textValidation(content);
			
			if(content.length() >= 500) {
				System.out.println("------------------------------------");
				System.out.println("내용은 500자 이내로 입력 해주세요");
				System.out.println("------------------------------------");
			}else {
				loop = false;
			}
		}
		
		System.out.println();
		System.out.println("1. 글 작성");
		System.out.println("0. 뒤로가기");
		
		System.out.println();
		System.out.print("메뉴 선택 : ");
		String select = scan.nextLine();
		
		if(select.equals("1")) {
			
			if(category.equals("자유게시판")) {
			
				String[] lastFreeBoard = Data.freeBoard.get(Data.freeBoard.size()-1).split(",");
				String lastNum = (Integer.parseInt(lastFreeBoard[0]) + 1) + "";
				
				String data = String.format("%s,%s,%s,%tF/%tT,%s,%s,%s"
											, lastNum
											, title
											, content
											, c
											, c
											, name == "" ? "관리자" : name
											, category
											, id);
				
				Data.freeBoard.add(data);
				
			}else if(category.equals("장터게시판")) {
				
				String[] lastFreeBoard = Data.marketBoard.get(Data.marketBoard.size()-1).split(",");
				int lastNum = Integer.parseInt(lastFreeBoard[0]) + 1;
				
				String data = String.format("%d,%s,%s,%tF/%tT,%s,%s,%s"
											, lastNum
											, title
											, content
											, c
											, c
											, name
											, category
											, id);
				
				Data.marketBoard.add(data);
				
			}else if(category.equals("문의게시판")) {
				
				String[] lastFreeBoard = Data.inquiryBoard.get(Data.inquiryBoard.size()-1).split(",");
				int lastNum = Integer.parseInt(lastFreeBoard[0]) + 1;
				
				String data = String.format("%d,%s,%s,%tF/%tT,%s,%s,%s"
											, lastNum
											, title
											, content
											, c
											, c
											, name
											, category
											, id);
				
				Data.inquiryBoard.add(data);
				
			}
			
			System.out.println();
			System.out.println("글을 추가하였습니다.");
			Data.pause();
			
		}else if(select.equals("0")){
			System.out.println();
			System.out.println("취소하였습니다.");
			Data.pause();
		}
	}
		
		
		
		//금지어 유효성 검사
		public String textValidation(String title) {
			
			String[] word = {"씨발", "시발", "ㅅㅂ", "시바", "또라이", "도라이", "또라이", "병신", "장애인"
							, "애미", "애비", "개새끼", "쉬바", "존나", "미친"}; 
			
			for(int i=0; i<word.length; i++) {
				title = title.replace(word[i], getText(word[i]));
			}
			
			return title;
		}


		public static String getText(String string) {
			
			String text = "";
			
			for(int i=0; i<string.length(); i++) {
				text += "*";
			}
			
			return text;
		}
		
		
		
		
		
		//게시판 글 자세히 보기
		public void boardView(String select, String boardType) {
			
			Scanner scan = new Scanner(System.in);
			
			if(boardType.equals("freeBoard") || boardType.equals("자유게시판")) {
			
				for(String board : Data.freeBoard) {
					
					String[] temp = board.split(",");
					
					if(temp[0].equals(select)) {
						System.out.println();
						System.out.println("=================================");
						System.out.printf("\t%s\n",temp[1]);
						System.out.println("=================================");
						System.out.println();
						
						System.out.println("[작성자]\t\t[날짜/시간]");
						System.out.printf("%s\t\t%s\n", temp[4], temp[3]);
						
						System.out.println();
						System.out.println("[내용]");
						System.out.println("------------------------------------------");
						System.out.println(temp[2]);
						System.out.println("------------------------------------------");
						
					}
					
					
				}
				
				
				if(LoginService.finalId.equals("관리자")) {
					
					System.out.println();
					System.out.println("1. 삭제하기");
					System.out.println("0. 뒤로가기");
					System.out.println();
					System.out.print("메뉴 선택 : ");
					
					String answer = scan.nextLine();
					
					if(answer.equals("1")) {
						BoardService boardService = new BoardService();
						boardService.boardDelete(select, boardType);
					}
					
				}else {
					
					System.out.println();
					System.out.println("0. 뒤로가기");
					System.out.println();
					System.out.print("메뉴 선택 : ");
					
					String answer = scan.nextLine();
					
				}
				
				
				
			}else if(boardType.equals("marketBoard") || boardType.equals("장터게시판")) {
				
				for(String board : Data.marketBoard) {
					
					String[] temp = board.split(",");
					
					if(temp[0].equals(select)) {
						System.out.println();
						System.out.println("=================================");
						System.out.printf("\t%s\n",temp[1]);
						System.out.println("=================================");
						System.out.println();
						
						System.out.println("[작성자]\t\t[날짜/시간]");
						System.out.printf("%s\t\t\t%s\n", temp[4], temp[3]);
						
						System.out.println();
						System.out.println("[내용]");
						System.out.println("------------------------------------------");
						System.out.println(temp[2]);
						System.out.println("------------------------------------------");
						
					}
					
					
				}
				
				
				if(LoginService.finalId.equals("관리자")) {
					
					System.out.println();
					System.out.println("1. 삭제하기");
					System.out.println("0. 뒤로가기");
					System.out.println();
					System.out.print("메뉴 선택 : ");
					
					String answer = scan.nextLine();
					
					if(answer.equals("1")) {
						BoardService boardService = new BoardService();
						boardService.boardDelete(select, boardType);
					}
					
				}else {
					System.out.println();
					System.out.println("0. 뒤로가기");
					System.out.println();
					System.out.print("메뉴 선택 : ");
					
					String answer = scan.nextLine();
				
				}
			
			}else if(boardType.equals("inquiryBoard") || boardType.equals("문의게시판")) {
				
				for(String board : Data.inquiryBoard) {
					
					String[] temp = board.split(",");
					
					if(temp[0].equals(select)) {
						System.out.println();
						System.out.println("=================================");
						System.out.printf("\t%s\n",temp[1]);
						System.out.println("=================================");
						System.out.println();
						
						System.out.println("작성자\t\t날짜/시간");
						System.out.printf("%s\t\t%s\n", temp[4], temp[3]);
						
						System.out.println();
						System.out.println("내용");
						System.out.println("------------------------------------------");
						System.out.println(temp[2]);
						System.out.println("------------------------------------------");
						
					}
					
				
				}
				
				if(LoginService.finalId.equals("관리자")) {
					
					System.out.println();
					System.out.println("1. 삭제하기");
					System.out.println("0. 뒤로가기");
					System.out.println();
					System.out.print("메뉴 선택 : ");
					
					String answer = scan.nextLine();
					
					if(answer.equals("1")) {
						BoardService boardService = new BoardService();
						boardService.boardDelete(select, boardType);
					}
					
				}else {
					System.out.println();
					System.out.println("0. 뒤로가기");
					System.out.println();
					System.out.print("메뉴 선택 : ");
					
					String answer = scan.nextLine();
				
				}
				
				
			}


		
		}
		
		
		//글 삭제
		public void boardDelete(String num, String type) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println();
			System.out.print("해당 글을 삭제하시겠습니까?(Y/N) : ");
			String answer = scan.nextLine();
			System.out.println();
			
			answer = answer.toLowerCase();
			
			if(answer.equals("y")) {
				
				try {
					
						
						
						if(type.equals("freeBoard")) {
							
							for(String freeboard : Data.freeBoard) {
								
								String[] freetemp = freeboard.split(",");
								
								if(freetemp[0].equals(num)) {
									
									Data.freeBoard.remove(freeboard);
									break;
									
								}
								
							}
							
						}else if (type.equals("marketBoard")){
							
							for(String freeboard : Data.marketBoard) {
								
								String[] freetemp = freeboard.split(",");
								
								if(freetemp[0].equals(num)) {
									
									Data.marketBoard.remove(freeboard);
									break;
									
								}
								
							}
							
						}else if (type.equals("inquiryBoard")){
							
							for(String freeboard : Data.inquiryBoard) {
								
								String[] freetemp = freeboard.split(",");
								
								if(freetemp[0].equals(num)) {
									
									Data.inquiryBoard.remove(freeboard);
									break;
									
								}
								
							}
							
						}
						
						System.out.println("삭제하였습니다.");
						Data.pause();
					
				} catch (Exception e) {
					System.out.println("BoardService.boardDelete");
					e.printStackTrace();
				}
				
			}else {
				System.out.println();
				System.out.println("취소하였습니다.");
				Data.pause();
			}
			
		}
			
	}
		

