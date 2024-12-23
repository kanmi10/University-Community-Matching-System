![header](https://capsule-render.vercel.app/api?type=venom&color=7B68EE&height=250&fontAlignY=45&section=header&text=대학%20커뮤니티%20매칭%20시스템&fontColor=DDDDDD&fontSize=50&animation=fadeIn&desc=(SSM)&descAlignY=65&descAlign=50&descSize=30)

> 대학생 커뮤니티, 관심사 매칭을 제공하는 Java 콘솔 프로젝트

> <i>팀 프로젝트(5명)</i>

> [🔗 개발 문서](https://drive.google.com/drive/folders/1c6P3SZljRrwSDpP97pn2EUvaDPBrGDES?usp=share_link)
 

<br>

## 개요

- **프로젝트 이름**: 대학 커뮤니티 매칭 시스템(SSM)
- **프로젝트 기간**: 2022. 09 ~ 10 / (리팩토링) 2024. 07 ~ 08
- **핵심 기능**: 관심 분야별 매칭(연애, 운동, 스터디), 학교 시설 대여, 게시판
- **기여 요약: 매칭 기능 구현, 관리자 매칭 관리, 리팩토링**

<br>

## 개발 환경

* Window 10 64bit, Mac OS
* JAVA (JDK 11.0.2 )
* Ecilpse IDE, IntelliJ IDEA

<br>


## 💡 주요 기능

### 매칭
- 관심 분야(운동, 연애, 스터디) 별 사용자가 원하는 조건에 맞는 매칭 시스템 제공

### 학교 시설 대여

- 날짜 시간에 따른 시설(체육관, 스터디룸) 대여 기능

### 게시판
- 게시판 카테고리 별 새 글 작성과 수정/삭제 및 조회


<br>

## 화면 구성

|메인 화면|매칭|
|:---:|:---:|
|<img src="https://github.com/user-attachments/assets/c7cc527c-8e80-4d98-910c-c2ab1742e989" width="500"/>|<img src="https://github.com/user-attachments/assets/77111687-a798-4861-ab52-a49183b6108d" width="500"/>|
|<div align="left">▪︎  메뉴를 통한 다양한 서비스 이용</div>|<div align="left">▪︎  원하는 상대방의 조건을 입력 시 매칭 시작</div>

|매칭 결과|관리자 매칭 조회|
|:---:|:---:|
|<img src="https://github.com/user-attachments/assets/be43093c-fe34-43cd-9907-1ef37589074c" width="500"/>|<img src="https://github.com/user-attachments/assets/81c38de5-fd11-4d98-9487-ec4dd7f5c832" width="500"/>|
|<div align="left">▪︎  조건에 맞는 매칭 상대 랜덤 매칭, 상대의 상세 정보 출력</div>|<div align="left">▪︎  관리자 계정 로그인 시, 매칭 시스템 사용자 조회 가능
</div>


<br>

## 진행하면서 마주친 어려움 & 체득한 부분
- **Refactoring**
    - [Matching 인터페이스 도입](https://velog.io/@kanmi10/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-Matching-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4-%EB%8F%84%EC%9E%85)
    - [ENUM을 활용한 카테고리 유지보수 향상](https://velog.io/@kanmi10/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-ENUM%EC%9D%84-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC-%EC%9C%A0%EC%A7%80%EB%B3%B4%EC%88%98-%ED%96%A5%EC%83%81)
    - [Java 날짜 라이브러리 활용 나이 변환 기능 리팩토링](https://velog.io/@kanmi10/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-JDK-81.8-%EB%82%A0%EC%A7%9C%EC%99%80-%EC%8B%9C%EA%B0%84-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC)
    - [매칭 사전 정보 입력 기능 리팩토링](https://velog.io/@kanmi10/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%82%AC%EC%A0%84-%EC%A0%95%EB%B3%B4-%EC%9E%85%EB%A0%A5-%EA%B8%B0%EB%8A%A5-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81)
    - [매칭 정보 삭제 기능 개선](https://velog.io/@kanmi10/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EB%A7%A4%EC%B9%AD-%EC%82%AC%EC%A0%84-%EC%A0%95%EB%B3%B4-%EC%82%AD%EC%A0%9C-%EA%B8%B0%EB%8A%A5-%EA%B0%9C%EC%84%A0)
    - [의존성 주입(DI)과 추상화를 활용한 매칭 서비스 리팩토링](https://velog.io/@kanmi10/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%9D%98%EC%A1%B4%EC%84%B1-%EC%A3%BC%EC%9E%85DI-%EC%A0%81%EC%9A%A9-%EA%B5%AC%EC%A1%B0-%EA%B0%9C%EC%84%A0) **
- **Problem**
    - [equals() 동일성과 동등성](https://velog.io/@kanmi10/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-equals-%EB%8F%99%EC%9D%BC%EC%84%B1%EA%B3%BC-%EB%8F%99%EB%93%B1%EC%84%B1)


![footer](https://capsule-render.vercel.app/api?type=waving&color=7B68EE&height=180&section=footer&text=%20&fontSize=90)
