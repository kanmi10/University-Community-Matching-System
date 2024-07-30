package com.project.ssm.matching;

abstract class Matching {

    private final String name;
    private final String emoticon;

    public Matching(String name, String emoticon) {
        this.name = name;
        this.emoticon = emoticon;
    }

    abstract void info();

    abstract boolean add();

    public String getName() {
        return name;
    }

    public String getEmoticon() {
        return emoticon;
    }

}
