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

public class TestMain {

    public static void main(String[] args) {

        Data.load();

        Map<String, Object> fieldMap = new HashMap<>();

        MatchingUser matchingUser = new MatchingUser();
        Field[] fields = matchingUser.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }

    }

}
