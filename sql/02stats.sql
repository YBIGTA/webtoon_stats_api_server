# 특정 웹툰의 특정 에피소드에서 특정 형태소가 나타나는 횟수에 따라 순위를 매기는 함수
# 1위부터 200위까지만 표시.
# 웹툰의 stat과 자주 나타나는 형태소의 상관관계 파악을 위해 필요.
DELIMITER //
CREATE PROCEDURE getMorphemeRank(IN title_id INT(11), IN episode_no INT(11))
BEGIN
	DROP TABLE IF EXISTS temp_table;

	CREATE TEMPORARY TABLE IF NOT EXISTS temp_table (
		title_id INT,
		episode_no INT,
		morpheme VARCHAR(8),
		morpheme_tag VARCHAR(15),
		count INT,
		rank INT
	);

	SET @row_number=0;

	INSERT INTO temp_table(title_id, episode_no, morpheme, morpheme_tag, count)
	SELECT
		title_id, episode_no, morpheme, morpheme_tag,  count(*)
	FROM
		comment_morpheme_analyzed T
	WHERE
		T.title_id=title_id and T.episode_no=episode_no
	GROUP BY
		morpheme, morpheme_tag
	ORDER BY
		count(*) desc
	LIMIT 200;

	INSERT INTO
		morpheme_rank(title_id, episode_no, morpheme, morpheme_tag, count, rank)
	SELECT
		title_id, episode_no, morpheme, morpheme_tag, count, (@row_number:=@row_number+1) as rank
	FROM
		temp_table;

END //
DELIMITER ;


# 각 화 업데이트 이후 1시간 안에 달리는 댓글 개수
# 현재 문제점: 웹툰 업데이트 시간이 아닌 첫댓글 시간을 기준으로 하고 있음
#         하지만 연재를 처음 시작하는 0화, 1화 정도는 
#         웹툰이 업데이트 되는 시점과 첫댓글 시점 간의 차이가 클 수 있음
#         그러므로 웹툰의 업데이트 시간도 크롤링되어야 할 필요가 있음
#         그래야 베댓 타이밍도 측정할 수 있음
DELIMITER //
CREATE PROCEDURE getCommentCountInFirstHour(IN title_id INT(11),IN  episode_no INT(11))
BEGIN
INSERT INTO comment_speed
SELECT title_id, episode_no, count(*) as speed
FROM comment C
WHERE C.title_id=title_id and C.episode_no=episode_no 
        and TIME(post_time) = TIME(SUBTIME(findFirstComment(title_id, episode_no), INTERVAL 1 HOUR));
END //
DELIMITER ;


# 각 에피소드의 첫 댓글 시간 가져오기
DELIMITER //
CREATE PROCEDURE getFirstCommentTime(IN title_id INT(11), IN episode_no INT(11))
BEGIN
SELECT min(T.post_time)
FROM comment T
WHERE T.title_id=title_id and T.episode_no=episode_no;
END //
DELIMITER ;


# 그 화에서 해당 형태소가 들어있는 댓글의 개수를 카운트
DELIMITER //
CREATE PROCEDURE getCommentCountHavingTargetMorpheme(IN title_id INT(11), IN episode_no INT(11), IN target_morpheme VARCHAR(15))
BEGIN
SELECT count(distinct comment_no)
FROM comment_morpheme_analyzed T
WHERE T.title_id=title_id and T.episode_no=episode_no and T.morpheme=target_morpheme
END //
DELIMITER ; (edited)

#그리고 그 화의 전체 댓글 중에서 그런 댓글의 비율을 구해준다 
DELIMITER //
CREATE PROCEDURE getCommentProportionHavingTargetMorpheme(IN title_id INT(11), IN episode_no INT(11), IN target_morpheme VARCHAR(15))
BEGIN
SELECT getCommentCountHavingTargetMorpheme(title_id,episode_no,target_morpheme) / count(distinct comment_no) 
FROM comment C
WHERE C.title_id=title_id and C.episode_no=episode_no
END //
DELIMITER ;


#그 에피소드의 첫댓글과 그 에피소드 베댓의 시간 차이 뽑아오기
DELIMITER //
CREATE PROCEDURE getBestCommentTimeFromFirstComment()
BEGIN

# 각화의 첫댓글로부터 베댓들이 몇 분 떨어져있는지 알아야 하니깐
# 각 베댓이 있는 에피소드의 첫댓글 시간을 가져와서
# 베댓의 post_time과의 차이를 봐야 한다 

SELECT TIMEDIFF(post_time, getFirstCommentTime(title_id, episode_no))
FROM comment
WHERE best_comment=1;

END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE getCommentCountsByEpisode(IN title_id INT(11))

SELECT C.episode_no, count(comment_no)
FROM comment C
WHERE C.title_id=title_id
GROUP BY C.episode_no;

END //
DELIMITER ;



# 각 에피소드 조회수 대비 댓글 수

# 