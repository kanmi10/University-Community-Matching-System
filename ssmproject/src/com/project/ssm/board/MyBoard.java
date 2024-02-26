package com.project.ssm.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginService;

public class MyBoard {

	private ArrayList<String> myBoard = new ArrayList<String>();
	private ArrayList<String> myGetBoard = new ArrayList<String>();
	
	public void mainInterface(){
		
		Scanner scan = new Scanner(System.in);
		
		int num = 1;
		
		boolean loop = true;
		
		while(loop) {
			
			myBoard.clear();
			num = 1;
			
			for(String board : Data.freeBoard) {
				
				String[] temp = board.split(",");
				
				if(temp[6].equals(LoginService.finalId)) {
					myBoard.add(board);
				}
				
			}

			
			for(String board : Data.marketBoard) {
				
				String[] temp = board.split(",");
				
				if(temp[6].equals(LoginService.finalId)) {
					myBoard.add(board);
				}
				
			}
	
			for(String board : Data.inquiryBoard) {
		
				String[] temp = board.split(",");
				
				if(temp[6].equals(LoginService.finalId)) {
					myBoard.add(board);
				}
		
			}
		
			System.out.println();
			System.out.println("========================================");
			System.out.println("              내가 쓴 글");
			System.out.println("========================================");
			System.out.println();
			
			System.out.println("번호\t제목\t\t날짜/시간\t\t학번\t\t작성자\t\t게시판");
			System.out.println("-------------------------------------------------------------"
								+ "----------------------------------");
			
			

			if(!myBoard.isEmpty()) {
				
				myGetBoard.clear();
				
				for(String board : myBoard) {
					
					String[] temp = board.split(",");
				
					String data = String.format("%d,%s,%s,%s,%s,%s,%s,%s"
												, num
												, temp[0]
												, temp[1]
												, temp[2]
												, temp[3]
												, temp[6]
												, temp[4]
												, temp[5]);
					
					
						myGetBoard.add(data);
					
					num++;
					
				}
				
				
				for(String frintBoard : myGetBoard) {
					
					String[] frinttemp = frintBoard.split(",");
					
					System.out.printf("%4s\t%s\t%s\t%4s\t%11s\t\t%5s\n"
										, frinttemp[0]
										, frinttemp[2]
										, frinttemp[4]
										, frinttemp[5]
										, frinttemp[6]
										, frinttemp[7]);
					
					
					
				}
				
				
			}else {
				System.out.println("작성한 글이 없습니다.");
			}
			
			System.out.println();
			System.out.print("글 선택(0.뒤로가기) : ");
			String select = scan.nextLine();
			
			if(!(select.equals("0"))) {
				
				boardView(select);
				
			}else if(select.equals("0")) {
				
				loop = false;
				
			}
		
		}
		
	}

	
	//내가 쓴 글 자세히 보기
	private void boardView(String num) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean check = false;
		
		for(String board : myGetBoard) {
			
			String[] temp = board.split(",");
			
			if(temp[0].equals(num)) {
				
				check = true;
				
				System.out.println();
				System.out.println("===========================");
				System.out.printf("\t%s\n",temp[2]);
				System.out.println("===========================");
				System.out.println();
				
				System.out.println("[작성자]\t\t[날짜/시간]");
				System.out.printf(" %s\t\t%5s\n", temp[6], temp[4]);
				
				System.out.println();
				System.out.println("[내용]");
				System.out.println("------------------------------------------");
				System.out.println(temp[3]);
				System.out.println("------------------------------------------");
				
				System.out.println();
				System.out.println("1. 글 수정");
				System.out.println("2. 글 삭제");
				System.out.println("0. 뒤로가기");
				System.out.println();
				
				System.out.print("메뉴 선택 : ");
				String select = scan.nextLine();
				
				if(select.equals("1")) {
					
					boardChange(num);
					
				}else if(select.equals("2")) {
					
					boardDelete(num);
					
				}else if(select.equals("0")) {
					
				}else {
					
					System.out.println("잘못 입력하였습니다. 전 페이지로 넘어갑니다.");
					
				}
				
				break;
				
			}
			
			check = false;
			
			
		}
		if(!check) {
			
			System.out.println("잘못 입력하였습니다.");
			
		}
		
		
		
	}

	
	//내가 쓴 글 수정
	private void boardChange(String num) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("해당 글을 수정 하시겠습니까?(Y/N) : ");
		String answer = scan.nextLine();
		
		answer = answer.toLowerCase();
		
		//1,16,adsfasdf,asdfefw,2019-07-26/11:27:11,18100321,양하은,장터게시판
		
		if(answer.equals("y")) {
			
			try {
				
				for(String board : myGetBoard) {
					
					String[] temp = board.split(",");
					
					if(temp[0].equals(num)) {
						
						String boardNumber = temp[0];
						String finalnum = temp[1];
						String boardTitle = temp[2];
						String boardContent = temp[3];
						String boardTime = temp[4];
						String boardId = temp[5];
						String boardName = temp[6];
						String boardType = temp[7];
						
						System.out.println();
						System.out.println("(입력 안하고 엔터 입력 시 그 전 내용 그대로 저장됩니다.)");
						System.out.print("제목 : ");
						String title = scan.nextLine();
						
						System.out.print("내용 : ");
						String content = scan.nextLine();
						
						if(!title.equals("")) {
							boardTitle = title;
						}
						
						if(!content.equals("")) {
							boardContent = content;
						}
						
						String data = String.format("%s,%s,%s,%s,%s,%s,%s,%s"
													, boardNumber
													, finalnum
													, boardTitle
													, boardContent
													, boardTime		
													, boardId
													, boardName
													, boardType);
						
						myGetBoard.set((Integer.parseInt(num))-1, data);
						
						//15,날씨 날씨 ,연락처 식사 학식 합격 ,2018-05-19/10:57:11,박재민,자유게시판,20116673
						String mainData = String.format("%s,%s,%s,%s,%s,%s,%s"
														, temp[1]
														, boardTitle
														, boardContent
														, boardTime
														, boardName
														, boardType
														, boardId);
						
						if(boardType.equals("자유게시판")) {
							Data.freeBoard.set((Integer.parseInt(temp[1])-1), mainData);
						}else if(boardType.equals("장터게시판")) {
							Data.marketBoard.set((Integer.parseInt(temp[1])-1), mainData);
						}else if(boardType.equals("문의게시판")) {
							Data.inquiryBoard.set((Integer.parseInt(temp[1])-1), mainData);
						}
						
					}
					
				}
				
				System.out.println("수정하였습니다.");
				
			} catch (Exception e) {
				System.out.println("MyBoard.boardChange");
				e.printStackTrace();
			}
			
		}
		
	}

	
	//내가 쓴 글 삭제
	public void boardDelete(String num) {
		
		//1,16,adsfasdf,asdfefw,2019-07-26/11:27:11,18100321,양하은,자유게시판
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("해당 글을 삭제하시겠습니까?(Y/N) : ");
		String answer = scan.nextLine();
		
		answer = answer.toLowerCase();
		
		if(answer.equals("y")) {
			
			try {
				
				for(int i=0; i<myGetBoard.size(); i++) {
					
					String[] temp = myGetBoard.get(i).split(",");
					
					if(temp[0].equals(num)) {
						
						myGetBoard.remove(myGetBoard.get(i));
						

					
					if(temp[7].equals("자유게시판")) {
						
						for(String freeboard : Data.freeBoard) {
							
							String[] freetemp = freeboard.split(",");
							
							if(freetemp[0].equals(temp[1])) {
								
								Data.freeBoard.remove(freeboard);
								
								System.out.println("삭제하였습니다.");
								Data.pause();
								
								break;
								
							}
							
						}
						
					}else if (temp[7].equals("장터게시판")){
						
						for(String freeboard : Data.marketBoard) {
							
							String[] freetemp = freeboard.split(",");
							
							if(freetemp[0].equals(temp[1])) {
								
								Data.marketBoard.remove(freeboard);
								
								System.out.println("삭제하였습니다.");
								Data.pause();
								
								break;
								
							}
							
						}
						
					}else if (temp[7].equals("문의게시판")){
						
						for(String freeboard : Data.inquiryBoard) {
							
							String[] freetemp = freeboard.split(",");
							
							if(freetemp[0].equals(temp[1])) {
								
								Data.inquiryBoard.remove(freeboard);
								
								System.out.println("삭제하였습니다.");
								Data.pause();
								
								break;
								
							}
							
						}
						
					}else {
						System.out.println();
						System.out.println("삭제에 실패하였습니다.");
						Data.pause();
					}
					
					break;
					}
					
					
				}
				
			} catch (Exception e) {
				System.out.println("MyBoard.boardDelete");
				e.printStackTrace();
			}
			
		}else {
			System.out.println();
			System.out.println("취소하였습니다.");
			Data.pause();
		}
		
	}
	
}
