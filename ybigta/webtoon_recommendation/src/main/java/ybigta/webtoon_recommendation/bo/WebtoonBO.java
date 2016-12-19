package ybigta.webtoon_recommendation.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ybigta.webtoon_recommendation.domain.Webtoon;
import ybigta.webtoon_recommendation.mapper.WebtoonMapper;

@Service
public class WebtoonBO {

	@Autowired
	private WebtoonMapper mapper;

	public int readWebtoonId(String title) {

		int i = 0;

		try {
			i = mapper.selectWebtoonId(title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public List<Webtoon> getRecommendWebtoonList(int titleId) {

		List<Webtoon> webtoonList = null;

		try {
			webtoonList = mapper.selectRecommendWebtoonList(titleId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return webtoonList;

	}
}
