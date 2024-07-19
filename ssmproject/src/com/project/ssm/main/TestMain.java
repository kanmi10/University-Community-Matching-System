package com.project.ssm.main;

import com.project.ssm.data.Data;
import com.project.ssm.login.LoginService;
import com.project.ssm.user.User;

import java.time.LocalDate;
import java.time.Period;

public class TestMain {

    public static void main(String[] args) {

        Data.load();

        LocalDate birthDate = LocalDate.of(1998, 4, 27);
        LocalDate nowDate = LocalDate.now();

        System.out.println("Period.between(nowDate, birthDate) = " + Period.between(birthDate, nowDate).getYears());
    }

}
