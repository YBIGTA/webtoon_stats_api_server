package ybigta.webtoon_recommendation.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ybigta.webtoon_recommendation.domain.Comment;
import ybigta.webtoon_recommendation.mapper.CommentMapper;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper mapper;

	public List<Comment> testProcedureCall() {

		List<Comment> commentList = null;

		Map<String, Object> inputMap = new HashMap<>();
		inputMap.put("likes", 10000);

		try {
			commentList = mapper.testProcedureCall(inputMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return commentList;

	}
}
