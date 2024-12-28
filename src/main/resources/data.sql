-- 기존 데이터
insert into article (title, content) values ('가가가', '11111');
insert into article (title, content) values ('나나나', '22222');
insert into article (title, content) values ('다다다', '33333');

-- article 테이블에 데이터 추가
insert into article (title, content) values ('당신의 인생 영화는?', '댓글 고');
insert into article (title, content) values ('당신의 소울 푸드는?', '댓글 고고');
insert into article (title, content) values ('당신의 취미는?', '댓글 고고고');

-- 4번 게시글의 댓글 추가
insert into comment (article_id, nickname, body) values (4, 'Park', '굿 윌 헌팅');
insert into comment (article_id, nickname, body) values (4, 'Kim', '아이 엠 샘');
insert into comment (article_id, nickname, body) values (4, 'Choi', '쇼생크 탈출');

-- 5번 게시글의 댓글 추가
insert into comment (article_id, nickname, body) values (5, 'Park', '치킨');
insert into comment (article_id, nickname, body) values (5, 'Kim', '샤브샤브');
insert into comment (article_id, nickname, body) values (5, 'Choi', '초밥');

-- 6번 게시글의 댓글 추가
insert into comment (article_id, nickname, body) values (6, 'Park', '조깅');
insert into comment (article_id, nickname, body) values (6, 'Kim', '유튜브 시청');
insert into comment (article_id, nickname, body) values (6, 'Choi', '독서');