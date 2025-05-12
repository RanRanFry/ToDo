create Table todos(
                      id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
                      userName VARCHAR(100) NOT NULL COMMENT '유저이름',
                      password VARCHAR(100) NOT NULL COMMENT '비밀번호',
                      contents VARCHAR(255) NOT NULL COMMENT '작성내용',
                      createdAt DATETIME NOT NULL COMMENT '생성일',
                      modifiedAt DATETIME NOT NULL COMMENT '수정일'
)