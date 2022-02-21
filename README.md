# inhatcBaekjoon

## 프로젝트 개요

![KakaoTalk_20211108_134345184](https://user-images.githubusercontent.com/78605779/140685845-9647634a-7d18-4f72-9cf6-c7123b49a75d.jpg)

## 사용 기술 스택

![기술스택](https://user-images.githubusercontent.com/78605779/141418721-cdfc39e3-10f6-4076-90bb-1a931f6bc595.PNG)

## ERD

![erd](https://user-images.githubusercontent.com/78605779/141423824-e92534be-9d92-4b90-b3a6-661392311dc2.PNG)

## 진행 사항

### 1주차(2021-09-13 ~ 2021-09-19)

<ul>
    <li>회원 등록 기능 구현</li>
    <li> Solved.ac API 연동 후 Rating 정보 불러오기</li>
</ul>

### 2주차(2021-09-27 ~ 2021-10-03)

<ul>
    <li>백준 학교 랭킹 정보 불러오기 계획 수정(크롤링 금지 및 API 미제공)</li>
    <li> Solved.ac API를 통해 나의 학교랭킹 및 점수 구하기</li>
    <li> 나의 학교보다 한 등수 높은 기관과의 점수차 구하여 출력(전체 랭킹, 대학 중 랭킹)</li>
</ul>

### 3주차(2021-10-04 ~ 2021-10-10)

<ul>
    <li>회원의 Sovled.ac 티어 정보 가져오기</li>
    <li> 회원의 점수가 더 높더라도 Daily 푼 문제 수로 순위를 확인할 수 있는 페이지 구현</li>
</ul>

### 4주차(2021-10-11 ~ 2021-10-17)

<ul>
    <li>Github API를 통해 회원의 github 커밋 수 가져오기</li>
    <li> Github 커밋 수가 많은 순으로 회원 정렬</li>
    <li> Github 커밋 수가 제일 많은 회원의 잔디밭 이미지 보여주기</li>
</ul>

# 2021-10-18 ~ 2021-10-22 중간고사 준비ㅎ

### 5주차(2021-10-23 ~ 2021-10-27)

<ul>
    <li>회원들끼리의 커뮤니티</li>
    <li> 스터디, 공모전, 팀 프로젝트등 대외활동 멤버를 구할 수 있는 게시판</li>
</ul>

### 6주차(2021-10-28 ~ 2021-11-03)

<ul>
    <li>회원 로그인 기능 구현(회원가입시 비밀번호 입력 추가 및 기본적으로 일반 유저로 등록)</li>
    <li> Spring Security를 통해 로그인 페이지 구현</li>
    <li> bcryptpasswordencoder를 통해 암호화된 password DB에 저장</li>
    <li> 알고리즘 및 자료구조 이론을 확인할 수 있는 페이지 구현</li>
    <li> 관리자 유저만 변경 가능(일반 유저는 변경 불가)</li>
</ul>

### 2022-02-21

+ Querydsl 추가 -> 커뮤니티 검색 부분 동적 쿼리 Querydsl로 변경

(다음에 해볼 것)
+ Spring batch 사용해보기
+ 현재 사용중인 Github API -> Github API For Java 라이브러리 변경 가능한지 확인 및 수정

## 구현 이미지

![1](https://user-images.githubusercontent.com/78605779/140614459-89a43211-23a1-49f7-a013-a74dc06d4c2d.PNG)
![2](https://user-images.githubusercontent.com/78605779/140614479-342cf395-afed-41a5-991b-4615d6305cc4.PNG)
![3](https://user-images.githubusercontent.com/78605779/140614483-dcbbfa9c-e7d6-4fff-8633-70ab231c1db6.PNG)
![Inked4_LI](https://user-images.githubusercontent.com/78605779/140614631-3eca37f8-8830-4e5f-b523-7346ca3b5b80.jpg)
![5](https://user-images.githubusercontent.com/78605779/140614485-11efaca5-3161-4d48-a1a2-6fa669c1a78e.PNG)
![6](https://user-images.githubusercontent.com/78605779/140614486-37460da4-39ac-458c-afd3-ab85d60e7d4f.PNG)
![Inked7_LI](https://user-images.githubusercontent.com/78605779/140614635-ccd0f0f5-d89a-42d0-b7fb-d6c27fb64103.jpg)
![8](https://user-images.githubusercontent.com/78605779/140614490-352f6a2d-6bd0-4186-93ec-dca16ad4b6a3.PNG)
![Inked9_LI](https://user-images.githubusercontent.com/78605779/140614638-4f052189-5c59-48fa-930a-0cd08e439366.jpg)
![Inked10_LI](https://user-images.githubusercontent.com/78605779/140614644-84ba0b11-0c9c-4e74-af9c-f68b81bfe7e4.jpg)
![11](https://user-images.githubusercontent.com/78605779/140614496-f1a301e2-4220-464d-9397-5a5ebbc00a33.PNG)
![12](https://user-images.githubusercontent.com/78605779/140614497-13b79e0a-4e5d-4a8e-9d58-6f314b3b41b7.PNG)
![13](https://user-images.githubusercontent.com/78605779/140614498-4f32580f-3507-489c-b936-68a46edbf780.PNG)
![14](https://user-images.githubusercontent.com/78605779/140614500-6a3fa97b-c928-4f25-a3fb-c4d499dad695.png)
![15](https://user-images.githubusercontent.com/78605779/140614501-eda894e7-db9f-40c0-aea7-c1f6c869c51b.PNG)
![16](https://user-images.githubusercontent.com/78605779/140614502-fdb415f0-7403-44fe-a18b-b42fec49ef5e.PNG)
![17](https://user-images.githubusercontent.com/78605779/140614503-f513315e-3b45-4c26-8d13-325dc7550a60.PNG)
