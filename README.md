# Coordin8 Backend API

프로젝트 협업 및 일정 조율을 위한 RESTful API 서버입니다.

## 📋 목차

- [프로젝트 소개](#프로젝트-소개)
- [기술 스택](#기술-스택)
- [주요 기능](#주요-기능)
- [시작하기](#시작하기)
- [API 문서](#api-문서)
- [환경 변수 설정](#환경-변수-설정)
- [프로젝트 구조](#프로젝트-구조)
- [배포](#배포)

## 🎯 프로젝트 소개

Coordin8은 팀 프로젝트 관리 및 일정 조율을 위한 백엔드 API 서버입니다. 프로젝트 생성, 멤버 관리, 스케줄 조율, 포커스 세션 추적 등의 기능을 제공합니다.

## 🛠 기술 스택

- **Java**: 17
- **Spring Boot**: 3.5.7
- **Spring Data JPA**: 데이터베이스 ORM
- **MariaDB**: 관계형 데이터베이스
- **Gradle**: 빌드 도구
- **SpringDoc OpenAPI**: API 문서화 (Swagger UI)
- **Lombok**: 보일러플레이트 코드 감소

## ✨ 주요 기능

### 1. 사용자 관리 (User)
- 사용자 생성, 조회, 수정, 삭제
- 사용자 프로필 관리

### 2. 프로젝트 관리 (Project)
- 프로젝트 생성, 조회, 수정, 삭제
- 프로젝트 코드 기반 조회

### 3. 프로젝트 멤버 관리 (Project Member)
- 프로젝트에 멤버 추가/제거
- 멤버별 역할 할당

### 4. 역할 관리 (Role)
- 역할 생성 및 관리
- 프로젝트별 역할 할당

### 5. 스케줄 관리 (Schedule)
- 가용성 등록
- 프로젝트별 스케줄 조회
- 일자/슬롯별 가용성 집계

### 6. 포커스 세션 (Focus Session)
- 포커스 세션 생성 및 종료
- 사용자별/프로젝트별 세션 조회
- 누적 시간 추적

### 7. 카드 관리 (Card)
- 카드 생성, 조회, 수정, 삭제

## 🚀 시작하기

### 필수 요구사항

- Java 17 이상
- Gradle 7.x 이상
- MariaDB 10.x 이상

### 설치 및 실행

1. **저장소 클론**
   ```bash
   git clone https://github.com/your-username/coordin8.git
   cd coordin8
   ```

2. **데이터베이스 설정**
   - MariaDB 데이터베이스 생성
   - `application-secret.yml` 파일 생성 (아래 환경 변수 설정 참고)

3. **애플리케이션 실행**
   ```bash
   # Gradle Wrapper 사용
   ./gradlew bootRun
   
   # 또는 JAR 파일 빌드 후 실행
   ./gradlew build
   java -jar build/libs/coordin8-0.0.1-SNAPSHOT.jar
   ```

4. **애플리케이션 접속**
   - 서버: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui/index.html

## 📚 API 문서

### Swagger UI
애플리케이션 실행 후 다음 URL에서 API 문서를 확인할 수 있습니다:
- **로컬**: http://localhost:8080/swagger-ui/index.html
- **배포 환경**: http://[서버-주소]:8080/swagger-ui/index.html

### 주요 API 엔드포인트

#### 사용자 API (`/api/v1`)
- `POST /api/v1/users` - 사용자 생성
- `GET /api/v1/users` - 전체 사용자 조회
- `GET /api/v1/users/{id}` - 사용자 조회
- `PUT /api/v1/users/{id}` - 사용자 수정
- `DELETE /api/v1/users/{id}` - 사용자 삭제

#### 프로젝트 API (`/api/v1/projects`)
- `POST /api/v1/projects` - 프로젝트 생성
- `GET /api/v1/projects` - 전체 프로젝트 조회
- `GET /api/v1/projects/{id}` - 프로젝트 조회
- `PUT /api/v1/projects/{id}` - 프로젝트 수정
- `DELETE /api/v1/projects/{id}` - 프로젝트 삭제

#### 프로젝트 멤버 API (`/api/v1/project-members`)
- `POST /api/v1/project-members` - 프로젝트 멤버 추가
- `GET /api/v1/project-members` - 전체 프로젝트 멤버 조회
- `GET /api/v1/project-members/{id}` - 프로젝트 멤버 조회
- `PUT /api/v1/project-members/{id}` - 프로젝트 멤버 수정
- `DELETE /api/v1/project-members/{id}` - 프로젝트 멤버 삭제

#### 역할 API (`/api/v1/roles`)
- `POST /api/v1/roles` - 역할 생성
- `GET /api/v1/roles` - 전체 역할 조회
- `GET /api/v1/roles/{id}` - 역할 조회
- `PUT /api/v1/roles/{id}` - 역할 수정
- `DELETE /api/v1/roles/{id}` - 역할 삭제

#### 스케줄 API (`/api/schedule`)
- `POST /api/schedule/availability` - 가용성 등록
- `GET /api/schedule/days/project/{projectId}` - 프로젝트별 스케줄 일자 조회
- `GET /api/schedule/availability/project/{projectId}/counts` - 가용성 집계

#### 포커스 세션 API (`/api/session`)
- `POST /api/session` - 포커스 세션 생성
- `POST /api/session/{sessionId}/end` - 포커스 세션 종료
- `GET /api/session/user/{userId}` - 사용자별 세션 조회
- `GET /api/session/project/{projectId}` - 프로젝트별 세션 조회

#### 카드 API (`/api/v1/cards`)
- `POST /api/v1/cards` - 카드 생성
- `GET /api/v1/cards` - 전체 카드 조회
- `GET /api/v1/cards/{id}` - 카드 조회
- `PUT /api/v1/cards/{id}` - 카드 수정
- `DELETE /api/v1/cards/{id}` - 카드 삭제

## ⚙️ 환경 변수 설정

### application-secret.yml 생성

프로젝트 루트의 `src/main/resources/` 디렉토리에 `application-secret.yml` 파일을 생성하고 다음 내용을 추가하세요:

```yaml
spring:
  datasource:
    url: jdbc:mariadb://[데이터베이스-호스트]:[포트]/[데이터베이스-이름]
    username: [데이터베이스-사용자명]
    password: [데이터베이스-비밀번호]
    driver-class-name: org.mariadb.jdbc.Driver
```

**⚠️ 중요**: `application-secret.yml` 파일은 민감한 정보를 포함하므로 Git에 커밋하지 마세요. `.gitignore`에 이미 추가되어 있습니다.

### 환경 변수로 설정 (권장)

환경 변수를 사용하여 데이터베이스 정보를 설정할 수도 있습니다:

```bash
export DB_URL=jdbc:mariadb://localhost:3306/coordin8
export DB_USERNAME=root
export DB_PASSWORD=your_password
```

그리고 `application-secret.yml`에서 다음과 같이 참조:

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    driver-class-name: org.mariadb.jdbc.Driver
```

## 📁 프로젝트 구조

```
coordin8/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/coordin8/
│   │   │       ├── config/          # 설정 클래스 (CORS, Swagger 등)
│   │   │       ├── controller/      # REST API 컨트롤러
│   │   │       ├── dto/             # 데이터 전송 객체
│   │   │       ├── entity/          # JPA 엔티티
│   │   │       ├── repository/      # 데이터베이스 리포지토리
│   │   │       └── service/         # 비즈니스 로직
│   │   └── resources/
│   │       ├── application.yml      # 애플리케이션 설정
│   │       └── application-secret.yml  # 민감한 정보 (Git 제외)
│   └── test/                        # 테스트 코드
├── build.gradle                     # Gradle 빌드 설정
├── .gitignore                       # Git 제외 파일 목록
└── README.md                        # 프로젝트 문서
```

## 🚢 배포

### AWS EC2 배포

1. **EC2 인스턴스 준비**
   - Java 17 설치
   - MariaDB 클라이언트 설치

2. **애플리케이션 빌드**
   ```bash
   ./gradlew clean build
   ```

3. **JAR 파일 업로드 및 실행**
   ```bash
   scp build/libs/coordin8-0.0.1-SNAPSHOT.jar user@ec2-instance:/path/to/app/
   ssh user@ec2-instance
   java -jar coordin8-0.0.1-SNAPSHOT.jar
   ```

4. **Elastic IP 설정** (권장)
   - EC2 인스턴스에 Elastic IP 할당하여 IP 주소 고정
   - 자세한 내용은 `EC2_IP_FIX_GUIDE.md` 참고

### 배포 관련 문서

- `DEPLOYMENT_ISSUES_GUIDE.md`: 배포 시 발생할 수 있는 문제 및 해결 방법
- `EC2_IP_FIX_GUIDE.md`: EC2 IP 주소 고정 가이드

## 🔒 보안

- 데이터베이스 비밀번호 등 민감한 정보는 `application-secret.yml`에 저장하며 Git에 커밋하지 않습니다.
- 프로덕션 환경에서는 환경 변수 사용을 권장합니다.
- CORS 설정은 `WebConfig.java`에서 관리됩니다.

## 📝 라이선스

이 프로젝트는 MIT 라이선스를 따릅니다.

## 👥 기여

이슈나 풀 리퀘스트를 환영합니다!

## 📞 문의

프로젝트 관련 문의사항이 있으시면 이슈를 등록해주세요.

---

**주의**: 이 프로젝트를 클론한 후 `application-secret.yml` 파일을 생성하여 데이터베이스 연결 정보를 설정해야 합니다.

