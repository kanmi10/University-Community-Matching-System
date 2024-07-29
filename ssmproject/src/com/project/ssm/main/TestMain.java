package com.project.ssm.main;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginService;
import com.project.ssm.matching.MatchingUser;
import com.project.ssm.user.User;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class TestMain {


    public static void main(String[] args) {

        Data.load();

        MatchingUser matchingUser = Data.matchingUserList.get(52);
        System.out.println(matchingUser);

        int minHeight = 170;
        int maxHeight = 180;
        int minWeight = 60;
        int maxWeight = 70;

        List<MatchingUser> loveUserList = new ArrayList<>();

        for (MatchingUser user : Data.matchingUserList) {

            if (matchingUser.getGender().equals(user.getGender())) {
                continue;
            }

            if (matchingUser.getCc().equals("N")) {
                if (user.getMajor().equals(matchingUser.getMajor())) {
                    System.out.println("user.getMajor() = " + user.getMajor());
                    continue;
                }
            }

            System.out.println("저장");
            loveUserList.add(user);

        }

        System.out.println("=====================================");
        for (MatchingUser user : loveUserList) {
            System.out.println("아이디: " + user.getId() + " 키: " + user.getHeight() + " 몸무게: " + user.getWeight() + " 성별: " + user.getGender() + " cc가능여부: " + user.getCc() + " 전공: " + user.getMajor());
        }


    }








}


