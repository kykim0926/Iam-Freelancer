/* 회원 정보 */
CREATE TABLE TB_MEMBER (
	LOGIN_ID NVARCHAR(50) NOT NULL, /* 회원 ID */
	LOGIN_PWD NVARCHAR(200) NOT NULL, /* 회원 비밀번호 (SHA-256 암호화) */
	NAME NVARCHAR(200) NOT NULL, /* 회원이름 */
	EMAIL NVARCHAR(200), /* 회원이메일 */
	PHONE_NUM NVARCHAR(50), /* 사무실 전화번호 */
	MOBILE_NUM NVARCHAR(50), /* 핸드폰번호 */
	ADDR NVARCHAR(500), /* 주소 */
	ADDR_DETAIL NVARCHAR(500), /* 상세주소 */
	POST_NUM NVARCHAR(50), /* 우편번호 */
	USER_TYPE CHAR(1), /* 기업:C 개인:I */
	BANK NVARCHAR(100) NOT NULL, /* 은행 */
	BANK_ACCOUNT CHAR(100), /* 은행 계좌 */
	REGIST_ROUTE NVARCHAR(50),          /* 가입경로 internet/SNS/recommand */
	USER_KIND CHAR(1), /* 전문가:S 의뢰인:C */
	REG_ID NVARCHAR(50), /* 등록자아이디 */
	REG_DATE CHAR(14), /* 등록일자 20140707231012 */
	UPDATE_ID NVARCHAR(50), /* 정보를마지막에수정한사람의아이디 */
	UPDATE_DATE CHAR(14), /* 정보를마지막에수정한시간 20140707231012 */
);

EXEC   sp_addextendedproperty 'MS_Description', '회원 정보', 'user', dbo, 'table',TB_MEMBER;

EXEC   sp_addextendedproperty 'MS_Description', '회원 ID', 'user', dbo, 'table', TB_MEMBER, 'column', LOGIN_ID;

EXEC   sp_addextendedproperty 'MS_Description', '회원사원번호등의 회원 로그인 아이디', 'user', dbo, 'table', TB_MEMBER, 'column', LOGIN_PWD;

EXEC   sp_addextendedproperty 'MS_Description', '이름', 'user', dbo, 'table', TB_MEMBER, 'column', NAME;

EXEC   sp_addextendedproperty 'MS_Description', '이메일', 'user', dbo, 'table', TB_MEMBER, 'column', EMAIL;

EXEC   sp_addextendedproperty 'MS_Description', '전화 번호', 'user', dbo, 'table', TB_MEMBER, 'column', PHONE_NUM;

EXEC   sp_addextendedproperty 'MS_Description', '핸드폰 번호', 'user', dbo, 'table', TB_MEMBER, 'column', MOBILE_NUM;

EXEC   sp_addextendedproperty 'MS_Description', '주소', 'user', dbo, 'table', TB_MEMBER, 'column', ADDR;

EXEC   sp_addextendedproperty 'MS_Description', '주소', 'user', dbo, 'table', TB_MEMBER, 'column', ADDR_DETAIL;

EXEC   sp_addextendedproperty 'MS_Description', '우편 번호', 'user', dbo, 'table', TB_MEMBER, 'column', POST_NUM;

EXEC   sp_addextendedproperty 'MS_Description', '기업:C 개인:I', 'user', dbo, 'table', TB_MEMBER, 'column', USER_TYPE;

EXEC   sp_addextendedproperty 'MS_Description', '은행', 'user', dbo, 'table', TB_MEMBER, 'column', BANK;

EXEC   sp_addextendedproperty 'MS_Description', '은행계좌', 'user', dbo, 'table', TB_MEMBER, 'column', BANK_ACCOUNT;

EXEC   sp_addextendedproperty 'MS_Description', '가입경로', 'user', dbo, 'table', TB_MEMBER, 'column', REGIST_ROUTE;

EXEC   sp_addextendedproperty 'MS_Description', '전문가:S 의뢰인:C', 'user', dbo, 'table', TB_MEMBER, 'column', USER_KIND;

EXEC   sp_addextendedproperty 'MS_Description', '수정자 ID', 'user', dbo, 'table', TB_MEMBER, 'column', UPDATE_ID;

EXEC   sp_addextendedproperty 'MS_Description', '수정일자', 'user', dbo, 'table', TB_MEMBER, 'column', UPDATE_DATE;

CREATE UNIQUE INDEX IDX_TB_MEMBER
	ON TB_MEMBER (
		LOGIN_ID ASC
	);

ALTER TABLE TB_MEMBER
	ADD
		CONSTRAINT PK_TB_MEMBER
		PRIMARY KEY (
			LOGIN_ID
		);

/* 코드 정보 */
CREATE TABLE TB_CODE (
	CD_ID CHAR(10) NOT NULL, /* 코드아이디 */
	CD_NM NVARCHAR(500), /* 코드에 이름 */
	CD_DESC NVARCHAR(2000), /* 코드에 대한 설명 */
	CD_SEQ INT, /* 코드정렬순서 */
	CD_USE_YN CHAR(1), /* Y : 사용, N : 미사용 */
);

EXEC   sp_addextendedproperty 'MS_Description', '코드정보', 'user', dbo, 'table',TB_CODE;

EXEC   sp_addextendedproperty 'MS_Description', '코드아이디', 'user', dbo, 'table', TB_CODE, 'column', CD_ID;

EXEC   sp_addextendedproperty 'MS_Description', '코드에 이름', 'user', dbo, 'table', TB_CODE, 'column', CD_NM;

EXEC   sp_addextendedproperty 'MS_Description', '코드에 대한 설명', 'user', dbo, 'table', TB_CODE, 'column', CD_DESC;

EXEC   sp_addextendedproperty 'MS_Description', '코드정렬순서', 'user', dbo, 'table', TB_CODE, 'column', CD_SEQ;

EXEC   sp_addextendedproperty 'MS_Description', 'Y : 사용, N : 미사용', 'user', dbo, 'table', TB_CODE, 'column', CD_USE_YN;

CREATE UNIQUE INDEX IDX_TB_CODE
	ON TB_CODE (
		CD_ID ASC
	);

ALTER TABLE TB_CODE
	ADD
		CONSTRAINT PK_TB_CODE
		PRIMARY KEY (
			CD_ID
		);

/* 권한 정보 */
CREATE TABLE TB_ROLE (
	ROLE_ID NVARCHAR(30) NOT NULL, /* ADMIN : 관리자, ANONYMOUS : 익명, MEMBER : 사용자 */
	ROLE_NM NVARCHAR(200), /* 권한명 */
);

EXEC   sp_addextendedproperty 'MS_Description', '업무시스템 사용자 권한, 서비스 이용자 권한, 데이터 베이스 관리자 권한, 스토리지 관리자 권한 등과 같은 보안상의 분류를 칭함', 'user', dbo, 'table',TB_ROLE;

EXEC   sp_addextendedproperty 'MS_Description', 'ADMIN : 관리자, ANONYMOUS : 익명, MEMBER : 사용자', 'user', dbo, 'table', TB_ROLE, 'column', ROLE_ID;

EXEC   sp_addextendedproperty 'MS_Description', '권한명', 'user', dbo, 'table', TB_ROLE, 'column', ROLE_NM;

CREATE UNIQUE INDEX IDX_TB_ROLE
	ON TB_ROLE (
		ROLE_ID ASC
	);

ALTER TABLE TB_ROLE
	ADD
		CONSTRAINT PK_TB_ROLE
		PRIMARY KEY (
			ROLE_ID
		);
		
/* 회원의 권한 정보*/
CREATE TABLE TB_MEMBER_ROLE (
	LOGIN_ID NVARCHAR(50) NOT NULL, /* 회원아이디 */
	ROLE_ID NVARCHAR(30) NOT NULL, /* 권한 아이디 */
);

EXEC   sp_addextendedproperty 'MS_Description', '회원역할정보', 'user', dbo, 'table',TB_MEMBER_ROLE;

EXEC   sp_addextendedproperty 'MS_Description', '회원아이디', 'user', dbo, 'table', TB_MEMBER_ROLE, 'column', LOGIN_ID;

EXEC   sp_addextendedproperty 'MS_Description', '권한아이디', 'user', dbo, 'table', TB_MEMBER_ROLE, 'column', ROLE_ID;

CREATE UNIQUE INDEX IDX_TB_MEMBER_ROLE
	ON TB_MEMBER_ROLE (
		LOGIN_ID ASC,
		ROLE_ID ASC
	);

ALTER TABLE TB_MEMBER_ROLE
	ADD
		CONSTRAINT PK_TB_MEMBER_ROLE
		PRIMARY KEY (
			LOGIN_ID,
			ROLE_ID
		);
		
ALTER TABLE TB_MEMBER_ROLE
	ADD
		CONSTRAINT FK_MEMBER_TO_MEMBER_ROLE
		FOREIGN KEY (
			LOGIN_ID
		)
		REFERENCES TB_MEMBER(
			LOGIN_ID
		);

ALTER TABLE TB_MEMBER_ROLE
	ADD
		CONSTRAINT FK_CODE_TO_MEMBER_ROLE
		FOREIGN KEY (
			ROLE_ID
		)
		REFERENCES TB_ROLE (
			ROLE_ID
		);