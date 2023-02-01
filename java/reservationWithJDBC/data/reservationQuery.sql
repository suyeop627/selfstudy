DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS cancel;
DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS performance;
DROP TABLE IF EXISTS member;
-- 테이블 생성
CREATE TABLE member (
	m_id VARCHAR(20)	PRIMARY KEY NOT NULL,
	m_pwd	VARCHAR(10)	NOT NULL,
	m_name	VARCHAR(10)	NOT NULL,
	m_phone	VARCHAR(20)	NOT NULL,
	m_birth	DATE	NOT NULL,
	m_account	VARCHAR(20)	NOT NULL
);

CREATE TABLE booking (
	b_no INT AUTO_INCREMENT	PRIMARY KEY,
	m_id	VARCHAR(20)	NOT NULL,
	b_seat	VARCHAR(5)	NOT NULL
);

CREATE TABLE cancel (
	c_no	INT	AUTO_INCREMENT PRIMARY KEY NOT NULL,
	b_no	INT	NOT NULL,
	m_id	VARCHAR(20)	NOT NULL
);

CREATE TABLE schedule (
	s_no	INT	AUTO_INCREMENT PRIMARY KEY NOT NULL,
	p_no	INT	NOT NULL,
	s_date	DATE	NOT NULL,
	s_start	TIME	NOT NULL,
	s_end	TIME	NOT NULL,
	s_hall	VARCHAR(10)	NOT NULL,
	s_seats	INT	NOT NULL
);

CREATE TABLE ticket (
	t_no	INT	AUTO_INCREMENT PRIMARY KEY NOT NULL,
	s_no	INT	NOT NULL,
	p_no	INT	NOT NULL,
  b_no INT NOT NULL,
  m_id VARCHAR(20) NOT NULL
);

CREATE TABLE performance (
  p_no INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	p_name	VARCHAR(20) NOT NULL,
  	p_age	INT	NOT NULL,
	p_actor	VARCHAR(50)	NOT NULL,
	p_rtime	TIME	NOT NULL,
	p_story	VARCHAR(255)	NOT NULL

);

-- 외래키 추가
ALTER TABLE schedule ADD CONSTRAINT FK_performance_TO_schedule_1 FOREIGN KEY (p_no)
REFERENCES performance (p_no) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ticket ADD CONSTRAINT FK_schedule_TO_ticket_1 FOREIGN KEY (s_no)
REFERENCES schedule (s_no) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ticket ADD CONSTRAINT FK_schedule_TO_ticket_2 FOREIGN KEY (p_no)
REFERENCES schedule (p_no) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE booking ADD CONSTRAINT FK_member_TO_booking_1 FOREIGN KEY (m_id)
REFERENCES member (m_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ticket ADD CONSTRAINT FK_booking_TO_ticket_1 FOREIGN KEY (b_no)
REFERENCES booking (b_no);

ALTER TABLE ticket ADD CONSTRAINT FK_booking_TO_ticket_2 FOREIGN KEY (m_id)
REFERENCES booking (m_id);

ALTER TABLE cancel ADD CONSTRAINT FK_booking_TO_cancel_1 FOREIGN KEY (b_no)
REFERENCES booking (b_no) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE cancel ADD CONSTRAINT FK_booking_TO_cancel_2 FOREIGN KEY (m_id)
REFERENCES booking (m_id) ON UPDATE CASCADE ON DELETE CASCADE;



-- 임시 데이터 입력
-- 회원 정보
SELECT * FROM member;

INSERT INTO member VALUES ('test01', '0000', '테스터01', '111-1111-1111', '1990-01-01', 111111111); 
INSERT INTO member VALUES ('test02', '0000', '테스터02', '222-2222-2222', '1998-02-02', 222222222);
INSERT INTO member VALUES ('test03', '0000', '테스터03', '333-3333-3333', '2001-03-03', 333333333);
INSERT INTO member VALUES ('test04', '0000', '테스터04', '444-4444-4444', '1997-04-04', 444444444);
INSERT INTO member VALUES ('test05', '0000', '테스터05', '555-5555-5555', '2003-05-05', 555555555);
INSERT INTO member VALUES ('test06', '0000', '테스터06', '666-6666-6666', '2003-06-06', 666666666);
INSERT INTO member VALUES ('test07', '0000', '테스터07', '777-7777-7777', '2003-07-07', 777777777);

-- 공연 정보 
SELECT * FROM performance;

INSERT INTO performance VALUES(null, '쉬어 매드니스', 15, '임정균, 문선화, 김해율, 강우진', '2:00:00','사건이 벌어진 바로 그 날,\r\n \t\t그날의 관객과 함께 살인사건을 \r\n \t\t해결하는 코믹추리 수사극!'); 
INSERT INTO performance VALUES(null, '옥탑방 고양이', 12,'문한별, 강이성, 박다진, 김윤환', '1:40:00','이중계약으로 시작된 한 지붕 \r\n \t\t두 남녀의 로멘틱한 청춘 로멘스'); 
INSERT INTO performance VALUES(null, '나의 PS파트너', 18,'도지훈, 이광호, 정수연, 송완', '2:10:00','오직 성인만 공감할 수 있는!\r\n \t\t꿈과 현실, 연애와 결혼\r\n \t\t그리고 사랑에 관한 거침없고 \r\n \t\t솔직한 STORY'); 

-- 일정
SELECT * FROM schedule;

-- 10/10
INSERT schedule VALUES(null,1, '2022-10-15','12:00', '14:00', 'A홀', 40);
INSERT schedule VALUES(null,2, '2022-10-15','13:00', '14:40', 'B홀', 50);
INSERT schedule VALUES(null,1, '2022-10-15','15:00', '17:00', 'A홀', 40);
INSERT schedule VALUES(null,2, '2022-10-15','15:00', '16:40', 'B홀', 50);
INSERT schedule VALUES(null,1, '2022-10-15','18:00', '20:00', 'A홀', 40);
INSERT schedule VALUES(null,3, '2022-10-15','17:00', '19:10', 'C홀', 60);
INSERT schedule VALUES(null,2, '2022-10-15','18:00', '19:40', 'B홀', 50);
INSERT schedule VALUES(null,3, '2022-10-15','20:00', '22:10', 'C홀', 60);
-- 10/11
INSERT schedule VALUES(null,1, '2022-10-16','12:00', '14:00', 'A홀', 40);
INSERT schedule VALUES(null,2, '2022-10-16','13:00', '14:40', 'B홀', 50);
INSERT schedule VALUES(null,1, '2022-10-16','15:00', '17:00', 'A홀', 40);
INSERT schedule VALUES(null,2, '2022-10-16','15:00', '16:40', 'B홀', 50);
INSERT schedule VALUES(null,1, '2022-10-16','18:00', '20:00', 'A홀', 40);
INSERT schedule VALUES(null,3, '2022-10-16','17:00', '19:10', 'C홀', 60);
INSERT schedule VALUES(null,2, '2022-10-16','18:00', '19:40', 'B홀', 50);
INSERT schedule VALUES(null,3, '2022-10-16','20:00', '22:10', 'C홀', 60);

-- 예약
SELECT * FROM booking;

INSERT booking VALUES(null,'test03', 2);
INSERT booking VALUES(null,'test01',3);
INSERT booking VALUES(null,'test01' ,23);
INSERT booking VALUES(null,'test01', 17);
INSERT booking VALUES(null,'test02', 33);
INSERT booking VALUES(null,'test02',  40);
INSERT booking VALUES(null,'test04',  37);
INSERT booking VALUES(null,'test05', 38);

-- 티켓
SELECT * FROM ticket;

INSERT ticket VALUES(null,1, 1, 1,'test03');
INSERT ticket VALUES(null,1, 1, 2,'test01');
INSERT ticket VALUES(null,1, 1, 3,'test01');
INSERT ticket VALUES(null,1, 1, 4,'test01');
INSERT ticket VALUES(null,1, 1, 5,'test02');
INSERT ticket VALUES(null,1, 1, 6,'test02');
INSERT ticket VALUES(null,1, 1, 7,'test04');
INSERT ticket VALUES(null,1, 1, 8,'test05');


commit;

