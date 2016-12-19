package ybigta.webtoon_recommendation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ybigta.webtoon_recommendation.bo.WebtoonBO;
import ybigta.webtoon_recommendation.domain.Webtoon;

@Controller
public class WebtoonController {

	@Autowired
	private WebtoonBO bo;

	@RequestMapping(value = "/api/webtoons/{title}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> show(@PathVariable(value = "title") String title) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		// 1. name을 이용해 webtoon id 받아오기
		int webtoonId = bo.readWebtoonId(title);

		// 2. webtoon id를 이용해 추천 목록 받아오기 
		List<Webtoon> recommendWebtoonList =  bo.getRecommendWebtoonList(webtoonId);
		
		if (recommendWebtoonList != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		// 3. 내려보내기
		resultMap.put("recommendWebtoonList", recommendWebtoonList);
		
		return resultMap;
	}
}
