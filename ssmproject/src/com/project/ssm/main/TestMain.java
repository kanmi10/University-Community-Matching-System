package com.project.ssm.main;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginService;
import com.project.ssm.matching.MatchingUser;
import com.project.ssm.user.User;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestMain {

    private static MatchingUser matchingUser;

    public static void main(String[] args) {

        Data.load();

        TestMain.matchingUser = Data.matchingUserList.get(Data.matchingUserList.size() - 1);

        System.out.println(Data.matchingUserList.size() - 1);
        System.out.println(matchingUser.equals(Data.matchingUserList.get(52)));
        System.out.println(matchingUser == (Data.matchingUserList.get(52)));

        getRandomValue();


    }

    public static int getRandomValue() {

        Random random = new Random();

        while (true) {

            int randomValue = 52;

            // 랜덤 인스턴스가 서로 같거나, 선호하는 운동 종목이 아니면 랜덤 정수 다시 구하기
            if (!isEqualToRandomInstance(randomValue) && !isEqualToExercise(randomValue)) {
                return randomValue;
            }

        }
    }

    public static boolean isEqualToRandomInstance(int randomValue) {
        MatchingUser user = Data.matchingUserList.get(randomValue);

        if (user == matchingUser) {
            System.out.println("같은 인스턴입니다!");
            return false;
        } else {
            System.out.println("다른 인스턴스입니다.");
            return false;
        }


    }

    public static boolean isEqualToExercise(int randomValue) {
        MatchingUser user = Data.matchingUserList.get(randomValue);

        if (user.getExercise().equals(matchingUser.getExercise())) {
            System.out.println("운동종목이 같습니다.");
            return true;
        } else {
            System.out.println("운동종목 통과!");
            return false;
        }

    }



}


