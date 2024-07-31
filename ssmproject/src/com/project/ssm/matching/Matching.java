package com.project.ssm.matching;

import java.util.List;

public abstract class Matching {

    private final String name;
    private final String emoticon;


    public Matching(String name, String emoticon) {
        this.name = name;
        this.emoticon = emoticon;
    }

    public abstract List<MatchingUser> findMatches();

    public abstract List<MatchingUser> filterAndAddMatchingUsers();

    public abstract void showMatch(List<MatchingUser> matchingUserList, MatchingUser otherUser);

    public abstract void saveMatchData(MatchingUser otherUser);

    public String getName() {
        return name;
    }

    public String getEmoticon() {
        return emoticon;
    }

}
