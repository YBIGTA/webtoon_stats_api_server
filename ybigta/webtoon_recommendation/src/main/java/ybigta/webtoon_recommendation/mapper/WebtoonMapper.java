package ybigta.webtoon_recommendation.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import ybigta.webtoon_recommendation.domain.Webtoon;

@Repository
public interface WebtoonMapper {

	public Webtoon selectWebtoon(int titleId) throws Exception;

	public int selectWebtoonId(String title) throws Exception;
	
	public List<Webtoon> selectRecommendWebtoonList(int titleId) throws Exception;
	
}
