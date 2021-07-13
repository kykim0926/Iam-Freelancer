/* ȸ�� ���� */
CREATE TABLE TB_MEMBER (
	LOGIN_ID NVARCHAR(50) NOT NULL, /* ȸ�� ID */
	LOGIN_PWD NVARCHAR(200) NOT NULL, /* ȸ�� ��й�ȣ (SHA-256 ��ȣȭ) */
	NAME NVARCHAR(200) NOT NULL, /* ȸ���̸� */
	EMAIL NVARCHAR(200), /* ȸ���̸��� */
	PHONE_NUM NVARCHAR(50), /* �繫�� ��ȭ��ȣ */
	MOBILE_NUM NVARCHAR(50), /* �ڵ�����ȣ */
	ADDR NVARCHAR(500), /* �ּ� */
	ADDR_DETAIL NVARCHAR(500), /* ���ּ� */
	POST_NUM NVARCHAR(50), /* �����ȣ */
	USER_TYPE CHAR(1), /* ���:C ����:I */
	BANK NVARCHAR(100) NOT NULL, /* ���� */
	BANK_ACCOUNT CHAR(100), /* ���� ���� */
	REGIST_ROUTE NVARCHAR(50),          /* ���԰�� internet/SNS/recommand */
	USER_KIND CHAR(1), /* ������:S �Ƿ���:C */
	REG_ID NVARCHAR(50), /* ����ھ��̵� */
	REG_DATE CHAR(14), /* ������� 20140707231012 */
	UPDATE_ID NVARCHAR(50), /* �������������������ѻ���Ǿ��̵� */
	UPDATE_DATE CHAR(14), /* �������������������ѽð� 20140707231012 */
);

EXEC   sp_addextendedproperty 'MS_Description', 'ȸ�� ����', 'user', dbo, 'table',TB_MEMBER;

EXEC   sp_addextendedproperty 'MS_Description', 'ȸ�� ID', 'user', dbo, 'table', TB_MEMBER, 'column', LOGIN_ID;

EXEC   sp_addextendedproperty 'MS_Description', 'ȸ�������ȣ���� ȸ�� �α��� ���̵�', 'user', dbo, 'table', TB_MEMBER, 'column', LOGIN_PWD;

EXEC   sp_addextendedproperty 'MS_Description', '�̸�', 'user', dbo, 'table', TB_MEMBER, 'column', NAME;

EXEC   sp_addextendedproperty 'MS_Description', '�̸���', 'user', dbo, 'table', TB_MEMBER, 'column', EMAIL;

EXEC   sp_addextendedproperty 'MS_Description', '��ȭ ��ȣ', 'user', dbo, 'table', TB_MEMBER, 'column', PHONE_NUM;

EXEC   sp_addextendedproperty 'MS_Description', '�ڵ��� ��ȣ', 'user', dbo, 'table', TB_MEMBER, 'column', MOBILE_NUM;

EXEC   sp_addextendedproperty 'MS_Description', '�ּ�', 'user', dbo, 'table', TB_MEMBER, 'column', ADDR;

EXEC   sp_addextendedproperty 'MS_Description', '�ּ�', 'user', dbo, 'table', TB_MEMBER, 'column', ADDR_DETAIL;

EXEC   sp_addextendedproperty 'MS_Description', '���� ��ȣ', 'user', dbo, 'table', TB_MEMBER, 'column', POST_NUM;

EXEC   sp_addextendedproperty 'MS_Description', '���:C ����:I', 'user', dbo, 'table', TB_MEMBER, 'column', USER_TYPE;

EXEC   sp_addextendedproperty 'MS_Description', '����', 'user', dbo, 'table', TB_MEMBER, 'column', BANK;

EXEC   sp_addextendedproperty 'MS_Description', '�������', 'user', dbo, 'table', TB_MEMBER, 'column', BANK_ACCOUNT;

EXEC   sp_addextendedproperty 'MS_Description', '���԰��', 'user', dbo, 'table', TB_MEMBER, 'column', REGIST_ROUTE;

EXEC   sp_addextendedproperty 'MS_Description', '������:S �Ƿ���:C', 'user', dbo, 'table', TB_MEMBER, 'column', USER_KIND;

EXEC   sp_addextendedproperty 'MS_Description', '������ ID', 'user', dbo, 'table', TB_MEMBER, 'column', UPDATE_ID;

EXEC   sp_addextendedproperty 'MS_Description', '��������', 'user', dbo, 'table', TB_MEMBER, 'column', UPDATE_DATE;

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

/* �ڵ� ���� */
CREATE TABLE TB_CODE (
	CD_ID CHAR(10) NOT NULL, /* �ڵ���̵� */
	CD_NM NVARCHAR(500), /* �ڵ忡 �̸� */
	CD_DESC NVARCHAR(2000), /* �ڵ忡 ���� ���� */
	CD_SEQ INT, /* �ڵ����ļ��� */
	CD_USE_YN CHAR(1), /* Y : ���, N : �̻�� */
);

EXEC   sp_addextendedproperty 'MS_Description', '�ڵ�����', 'user', dbo, 'table',TB_CODE;

EXEC   sp_addextendedproperty 'MS_Description', '�ڵ���̵�', 'user', dbo, 'table', TB_CODE, 'column', CD_ID;

EXEC   sp_addextendedproperty 'MS_Description', '�ڵ忡 �̸�', 'user', dbo, 'table', TB_CODE, 'column', CD_NM;

EXEC   sp_addextendedproperty 'MS_Description', '�ڵ忡 ���� ����', 'user', dbo, 'table', TB_CODE, 'column', CD_DESC;

EXEC   sp_addextendedproperty 'MS_Description', '�ڵ����ļ���', 'user', dbo, 'table', TB_CODE, 'column', CD_SEQ;

EXEC   sp_addextendedproperty 'MS_Description', 'Y : ���, N : �̻��', 'user', dbo, 'table', TB_CODE, 'column', CD_USE_YN;

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

/* ���� ���� */
CREATE TABLE TB_ROLE (
	ROLE_ID NVARCHAR(30) NOT NULL, /* ADMIN : ������, ANONYMOUS : �͸�, MEMBER : ����� */
	ROLE_NM NVARCHAR(200), /* ���Ѹ� */
);

EXEC   sp_addextendedproperty 'MS_Description', '�����ý��� ����� ����, ���� �̿��� ����, ������ ���̽� ������ ����, ���丮�� ������ ���� ��� ���� ���Ȼ��� �з��� Ī��', 'user', dbo, 'table',TB_ROLE;

EXEC   sp_addextendedproperty 'MS_Description', 'ADMIN : ������, ANONYMOUS : �͸�, MEMBER : �����', 'user', dbo, 'table', TB_ROLE, 'column', ROLE_ID;

EXEC   sp_addextendedproperty 'MS_Description', '���Ѹ�', 'user', dbo, 'table', TB_ROLE, 'column', ROLE_NM;

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
		
/* ȸ���� ���� ����*/
CREATE TABLE TB_MEMBER_ROLE (
	LOGIN_ID NVARCHAR(50) NOT NULL, /* ȸ�����̵� */
	ROLE_ID NVARCHAR(30) NOT NULL, /* ���� ���̵� */
);

EXEC   sp_addextendedproperty 'MS_Description', 'ȸ����������', 'user', dbo, 'table',TB_MEMBER_ROLE;

EXEC   sp_addextendedproperty 'MS_Description', 'ȸ�����̵�', 'user', dbo, 'table', TB_MEMBER_ROLE, 'column', LOGIN_ID;

EXEC   sp_addextendedproperty 'MS_Description', '���Ѿ��̵�', 'user', dbo, 'table', TB_MEMBER_ROLE, 'column', ROLE_ID;

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