package com.project.ssm.matching;

public class Match {

    private final Matching matching; // ExerciseMatch, LoveMatch, StudyMatch

    public Match(Matching matching) {
        this.matching = matching;
    }


    public void start(MatchingUser matchingUser) {
        matching.info();
    }


}
