package com.project.ssm.matching;

public enum Study {
    //[1. 자바 2. JSP 3. C언어 4. 파이썬 5. C# 6. DB]
    JAVA("자바"),
    JSP("JSP"),
    C("C언어"),
    Python("파이썬"),
    CShop("C#"),
    DataBase("DB");

    private final String study;

    Study(String study) {
        this.study = study;
    }

    public String getName() {
        return study;
    }

}
