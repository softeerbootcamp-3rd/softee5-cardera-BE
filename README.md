# 🚙 카더라 (Cardera)

<img width="2917" alt="KakaoTalk_Photo_2024-01-09-23-11-49" src="https://github.com/softeerbootcamp-3nd/softee5-cardera-BE/assets/70956926/36d9aef7-683a-4fd1-9f1d-0e785082a1f9">

## About Project
### 🚙 카더라 (Cardera)
카풀 답례가 고민인 사회 초년생들의 스트레스를 줄여주는 카풀 답례 추천 서비스

> 🌟 프론트 배포 링크: https://cardera.netlify.app
> 
> 🌟 백엔드 서버 배포 링크: https://server.jeongjin.me

## 📝 Main Feature
### **간단한 카풀 비용 계산** 💵
* 3가지 필수정보(출발지 / 도착지, 동승자 수, 카풀 횟수)를 입력하기만 하면 간단하게 비용 계산
* 네이버 유류비 계산 API 이용

<img src="https://github.com/softeerbootcamp-3nd/softee5-cardera-BE/assets/70956926/4697f8f9-362e-432c-9856-f10218680a45" height="500px">

<img src="https://github.com/softeerbootcamp-3nd/softee5-cardera-BE/assets/70956926/938e7ecd-ca48-4ed8-a8e3-c03b9aaf3db3" height="500px">

<img src="https://github.com/softeerbootcamp-3nd/softee5-cardera-BE/assets/70956926/17587067-6912-43b3-9b3d-d9f1159d8fde" height="500px">

<img src="https://github.com/softeerbootcamp-3nd/softee5-cardera-BE/assets/70956926/a3ae81bb-22bb-4fd5-8969-d0070388b3a2" height="500px">


### **비용에 맞는 선물 추천** 🎁
* 필터로 맞춤 선물 추천
* 선물 고른 사람의 수 데이터 제공
* 선물 추천 큐레이션 제공

<img src="https://github.com/softeerbootcamp-3nd/softee5-cardera-BE/assets/70956926/4f9b6add-9642-4a1c-84d7-362dabfccd06" height="500px">

<img src="https://github.com/softeerbootcamp-3nd/softee5-cardera-BE/assets/70956926/9aba3a6e-6fb0-43e0-a0e9-15b5f5814ed8" height="500px">


### **감사 문구 추천** 🙏
* 관계에 따른 맞춤 문구 추천(상사/동료/후배)
* 선물 바코드, 감사 텍스트 이미지로 저장
* 감사 텍스트 수정 기능

<img src="https://github.com/softeerbootcamp-3nd/softee5-cardera-BE/assets/70956926/a8270704-62b4-4b8d-a343-c054847a77dc" height="500px">

## 🧑‍💻 Dev Stack
### Frontend
![Typescript](https://img.shields.io/badge/typescript-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![Parcel](https://img.shields.io/badge/parcel-000000?style=for-the-badge&logo=parcel&logoColor=white)
![tailwindcss](https://img.shields.io/badge/tailwindcss-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white)

### Backend
![SpringBoot](https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white)
![H2](https://img.shields.io/badge/H2-4479A1?style=for-the-badge&logo=H2&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![AWS RDS](https://img.shields.io/badge/aws_rds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white)

### Deploy
#### FE
![Netlify](https://img.shields.io/badge/netlify-00C7B7?style=for-the-badge&logo=netlify&logoColor=white)

#### BE
![AWS EC2](https://img.shields.io/badge/AWS_EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white)
![AWS S3](https://img.shields.io/badge/aws_s3-569A31?style=for-the-badge&logo=amazons3&logoColor=white)
![AWS CodeDeploy](https://img.shields.io/badge/aws_codedeploy-FF9900?style=for-the-badge&logo=awscodedeploy&logoColor=white)
![GithubActions](https://img.shields.io/badge/Github_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)


## 🛠 System Architecture
![시스템아키텍쳐](https://github.com/softeerbootcamp-3nd/softee5-cardera-BE/assets/70956926/17fb3a74-1fc8-4eca-b234-2ad195f7cf15)

## Archive
### 📝 [API 명세서](https://www.notion.so/b-side/API-07528cac80cd4b769ad473e414d37982?pvs=4)
### 🌳 [Git 브랜치 전략](https://www.notion.so/b-side/Git-fb3b21b06b0b4df792beb4e7e04702dd?pvs=4)
### 📏 [그라운드 룰](https://www.notion.so/b-side/ab1c45455ac441fda921d35430238c75?pvs=4)

### 💾 Database Schema
![image](https://github.com/softeerbootcamp-3nd/softee5-cardera-BE/assets/70956926/dbeefec3-0c66-4ada-a47a-1a1d6b5303a8)


### 💻 백엔드 구현 기능
* 네이버 `directions5` API를 이용해서 유류비를 계산해주는 API
* 유저가 선택한 수고비 옵션을 저장하는 API
* 운영환경과 개발환경을 분리해서 개발환경에서는 `H2`, 운영환경에서는 `AWS RDS` `MySQL` 인스턴스 이용
* `AWS EC2`에 백엔드 서버 배포 진행
* `GitHub Actions`와 `AWS S3`, `AWS CodeDeploy`를 사용하여 CI/CD 파이프라인 구축
* `NGINX`와 `letsencrypt`를 사용하여 HTTPS 설정
* `JUnit`, `AssertJ`, `mockito` 라이브러리를 이용하여 유닛 테스트코드 작성


## ✨ About Team
### 현대 소프티어 부트캠프 3기 워밍업 프로젝트 소프티5 - 기디개팀(4팀)



|      기획       |          디자인         |      개발(FE)         |      개발(BE)       |          개발(BE)         |       개발(BE)         |                                                                                                            
| :------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|   <img width="160px" src="" />    |                      <img width="160px" src="" />    |                   <img width="160px" src="https://avatars.githubusercontent.com/u/33178048?v=4"/>   |                   <img width="160px" src="https://avatars.githubusercontent.com/u/70956926?v=4"/>   |                   <img width="160px" src="https://avatars.githubusercontent.com/u/102792381?v=4"/>   |                   <img width="160px" src="https://avatars.githubusercontent.com/u/130366955?v=4"/>   |
| 김현우 | 서유진 | [오정진](https://github.com/ojj1123) | [윤수지](https://github.com/Yoon-Suji) | [김성원](https://github.com/swkim0911) | [정석인](https://github.com/jjyk999) |
