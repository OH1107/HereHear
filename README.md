# Here Hear

![image](https://user-images.githubusercontent.com/67505208/136942485-342d2a23-2794-430e-8995-fd4521c82e7a.png)

## 서비스 개요
- 독서 습관을 만드는 데에 도움을 주는 서비스 - HereHear

![image](https://user-images.githubusercontent.com/67505208/136942693-24a32523-d4ee-49b9-9e80-9944f8557131.png)

- Google Play 등록 ~~https://play.google.com/store/apps/details?id=com.ssafy.herehear~~ (EC2 인스턴스 만료로 인한 배포 중단)

![image](https://user-images.githubusercontent.com/67505208/136943366-e1fdc2f5-59ea-4b23-b793-305aa6989d85.png)


## 주요 기능
- 오디오 북 서비스
  - 업로드한 사진의 글자를 인식하고 음성으로 재생
  - OCR + TTS (Kakao OCR Api / Easy OCR + gTTS)  

![image](https://user-images.githubusercontent.com/67505208/136942958-bf99f572-d597-4a98-bedf-9acaf23290c4.png)

- 도서 추천 서비스
  - 사용자의 평점 데이터 분석을 기반한 도서 추천 (행렬분해를 이용한 잠재요인 협업 필터링)  

![image](https://user-images.githubusercontent.com/67505208/136943080-17c4670d-9f66-4218-9066-d1a2f46d28ff.png)

- 캘린더 서비스
  - 날짜, 시간, 감상평을 기록하고 저장 (독서를 커밋하며 꾸준한 기록!)  

![image](https://user-images.githubusercontent.com/67505208/136943196-5a6409f6-d0c3-42c8-8caf-09e114d82909.png)


## 기술 스택 및 프로젝트 구조

![image](https://user-images.githubusercontent.com/67505208/136943588-19242840-75d1-4833-857a-bd5f1543ac28.png)

- BE 프레임워크
  - Spring Boot: 회원 인증 및 관리, 캘린더, 도서 관련 등 일반 RESTful Api 요청, 응답용 서버
  - Django: OCR, TTS, 추천 알고리즘 요청, 응답용 서버

- DB
  - MySQL: 일반 Api 요청, 응답에 필요한 데이터 저장 공간
  - Redis: 회원 인증 시, Refresh Tocken(JWT)를 일정 기간 보관하기 위한 캐시메모리 DB

- CI/CD
  - GitLab master 브랜치에 merge/push -> Jenkins를 통한 자동 빌드 및 (배포) -> 성공실패 여부를 웹훅을 통한 MatterMost에 전달


## 팀원 및 역할 분담
- 오승철 (팀장)
  - BE
  - 서버 배포
  - CI/CD
  - 추천 알고리즘

- 조효정
  - BE
  - 서버 배포
  - OCR

- 양현승
  - Android
  - 앱 배포
  - TTS

- 송한샘
  - Android
  - 앱 배포
  - UCC 제작

## 상세 개발 내용
- Spring Boot 프레임워크를 사용한 RESTful API 개발
  - 캘린더 및 회원 서비스 관련 API 개발
- Refresh Token, Access Token 방식의 JWT 인증, 인가 구현
  - Refresh Token의 경우 Redis 사용
- EC2 서버에 Django, Spring Boot 서버 배포
  - Django의 경우 Nginx, uWSGI 연동 배포
- EC2 서버에 MySQL DB 구축 및 서버와 연동
- Jenkins를 사용한 자동 배포 파이프라인 구축
  - GitLab의 Master 브랜치에 이벤트 발생 시, 새로 build하고 배포하는 flow
- 행렬분해를 통한 잠재요인 협업필터링의 추천 알고리즘 구현
  - 사용자들이 좋아하는 도서에 대한 여러 잠재요인으로 분석
  - 아직 특정 도서를 읽지 않은 경우 높은 평점을 부여할 것으로 추정되는 사용자에게 해당 도서 추천

## 개발 기간

- 2021.08.30 ~ 2021.10.08

- 1주 단위 Jira Sprint Report

![image](https://user-images.githubusercontent.com/67505208/136945108-142bbf30-36d4-4e89-920b-8df1302de834.png)






