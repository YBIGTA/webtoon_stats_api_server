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
