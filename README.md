1.  JSP 파일 업로드 개념
 - 파일 업로드 기능이란 게시판, 프로필 사진 설정 등으로 웹 서버에 파일을 업로드 하는 기능
 - DB연동, 웹 서버의 디스크 자원 차지 등 다양한 기능이 수행됨
 - 파일 업로드는 실제로 취약점이나 오류가 제일 많이 발생하는 부분이다.

2. JSP 파일 업로드에 필요한 라이브러리
  - JSP에서 파일 업로드를 위해서는 COS 라이브러리가 필요
  - COS 라이브러리의 Multipart Request 클래스를 사용
  - 설치 경로 : http://servlets.com/cos/
     

3. JSP 파일 업로드 기능 구현 순서
  - JSP 파일 업로드 기능 구현 순서
    1) 데이터베이스 구축
    2) 업로드 양식 페이지 작성
    3) 데이터베이스 연동 클래스 작성
    4) 업로드 처리 페이지 작성
    5) 파일 다운로드 페이지 작성
    6) 보안 코딩 적용 -> 시큐어링 코드
    

  - JSP 파일 업로드 기능 구현 순서
    1) 파일을 다운로드 한 횟수 저장
    2) 다중 파일 업로드 구현

4. 데이터베이스 구축 - MySQL

   Workbench에서 root로 로그인!!

   
          -- 실습용 데이터베이스 생성
          create database file;
          use file;

          -- 파일 업로드 테이블 생성
          create table file(
          fileName varchar(200),
          fileRealName varchar(200)
          );


          -- Workbench에서 root로 로그인 한 후 테이블을 변경하고 확인
          use file;
          alter table file add(downloadCount int);
          desc file;

          set SQL_SAFE_UPDATES = 0;  # disable safe mode
          delete from file;
          set SQL_SAFE_UPDATES = 1;  # enable safe mode

6. 보안 코딩(Secure Coding) 적용
 - 파일 업로드 취약점으로 대표적인 것을 웹 쉘(Web Shell) 업로드 취약점
 - ASP, JSP, PHP 모든 유형에서 적용이 가능한 기법
 - 공격자는 웹 쉘을 통해 서버의 명렁어를 직접 실행시키기게 됨
