package com.project.ssm.matching;

import com.project.ssm.user.User;

public class MatchingUser extends User {

	private int age;		// 나이
	private String gender;	// 성별
	private int height;		// 키
	private int weight;		// 몸무게
	private String cc;		// 과CC 가능여부
	private String exercise;// 운동종목
	private double grade;	// 학점
	private String study;	// 공부

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getStudy() {
		return study;
	}
	public void setStudy(String study) {
		this.study = study;
	}


	@Override
	public String toString() {

		return "                              [나의 정보]" + '\n' +
				"                           1. 키: " + height + '\n' +
				"                           2. 몸무게: " + weight + '\n' +
				"                           3. CC 가능여부: " + cc + '\n' +
				"                           4. 선호 운동: " + exercise + '\n' +
				"                           5. 학점: " + grade + '\n' +
				"                           6. 선호 공부: " + study;
	}
}
