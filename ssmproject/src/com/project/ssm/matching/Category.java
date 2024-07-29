package com.project.ssm.matching;

public enum Category {

    Exercise("운동"),
    Love("연애"),
    Study("공부");

    private final String name;

    public String getName() {
        return name;
    }

    Category(String name) {
        this.name = name;
    }
}
