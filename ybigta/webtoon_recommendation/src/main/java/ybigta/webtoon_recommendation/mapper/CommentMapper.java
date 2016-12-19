package ybigta.webtoon_recommendation.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ybigta.webtoon_recommendation.domain.Comment;

@Repository
public interface CommentMapper {

	public List<Comment> testProcedureCall(Map<String, Object> inputMap) throws Exception;
}
