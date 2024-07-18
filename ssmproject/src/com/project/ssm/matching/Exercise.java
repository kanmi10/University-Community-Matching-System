package com.project.ssm.matching;

public enum Exercise {

    BASKETBALL("농구", "🏀"),
    SOCCER("축구", "⚽︎"),
    BADMINTON("배드민턴","🏸"),
    UPPER_BODY("상체", "💪"),
    LOVER_BODY("하체", "🦵");

    private final String name;
    private final String emoticon;

    Exercise(String name, String emoticon) {
        this.name = name;
        this.emoticon = emoticon;
    }

    public String getName() {
        return name;
    }

    public String getEmoticon() {
        return emoticon;
    }
}
