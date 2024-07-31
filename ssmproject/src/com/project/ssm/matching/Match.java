package com.project.ssm.matching;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginService;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Match {

    private final Matching matching; // ExerciseMatch, LoveMatch, StudyMatch
    private final Scanner scanner = new Scanner(System.in);

    public Match(Matching matching) {
        this.matching = matching;
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.printf("-----------------------------⋆⁺₊⋆ %s ⋆⁺₊⋆-----------------------------\n", matching.getEmoticon());
            System.out.printf("                            %s 매칭 추가입력\n", matching.getName());
            System.out.println("----------------------------------------------------------------------");
            System.out.println();
            System.out.printf("                             1. 매칭하기 %s\n", matching.getEmoticon());
            System.out.println("                             0. 뒤로가기 ↩️");
            System.out.println();
            System.out.println("----------------------------------------------------------------------");
            System.out.print("                             ▶ 메뉴 선택: ");

            switch (scanner.nextLine()) {
                case "1":
                    if (Data.isMatchingListEmpty()) {
                        break;
                    }

                    if (!add()) {
                        System.out.println("매칭에 실패했습니다.");
                    }
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

    private boolean add() {

        // 1. 조건에 맞는 상대 찾기(연애 - 요구 키, 몸무게 조건 | 운동 - 같은 선호 운동 | 공부 - 같은 선호 공부종목)
        // 2. 찾아서 새로운 matchingUserList에 담기
        List<MatchingUser> matchingUserList = matching.findMatches();

        //3. 조건에 맞는 매칭 상대가 없다면 안내문구 출력후 나가기
        if (matchingUserList.isEmpty()) {
            System.out.println("조건에 맞는 상대를 찾지 못했습니다.");
            return false;
        } else {
            System.out.printf("%s️ 매칭이 완료되었습니다! %s️", matching.getEmoticon(), matching.getEmoticon());
            Data.pause();
        }

        // 4. matchingUserList 중 랜덤으로 한명 구하기
        MatchingUser otherUser = matchingUserList.get(getRandomValue(matchingUserList));


        // 5. 매칭이 완료됐다면, 매칭 결과 내용을 저장하겠냐 사용자에게 물어보고 응답하면 매칭 결과 내역 MatchingResultUserList에 담고 파일 저장
        matching.showMatch(matchingUserList, otherUser);
        matching.saveMatchData(otherUser);

        return true;
    }



    private int getRandomValue(List<MatchingUser> userList) {

        Random random = new Random();

        while (true) {

            int randomValue = random.nextInt(userList.size() - 1);

            if (!isEqualToRandomInstance(randomValue)) {
                return randomValue;
            }

        }
    }

    private boolean isEqualToRandomInstance(int randomValue) {
        MatchingUser user = Data.matchingUserList.get(randomValue);
        return user.getId().equals(LoginService.finalId); //TODO 리팩토링 필요 user == matchingUser
    }


}
