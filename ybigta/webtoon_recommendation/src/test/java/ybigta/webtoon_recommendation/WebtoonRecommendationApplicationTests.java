package ybigta.webtoon_recommendation;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ybigta.webtoon_recommendation.bo.CommentBO;
import ybigta.webtoon_recommendation.bo.WebtoonBO;
import ybigta.webtoon_recommendation.domain.Comment;
import ybigta.webtoon_recommendation.domain.Webtoon;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebtoonRecommendationApplicationTests {

	@Autowired
	private WebtoonBO webtoonBo;
	
	@Autowired
	private CommentBO commentBo;
	
	@Test
	public void test2() {
		List<Comment> testList = commentBo.testProcedureCall();
		
		for(Comment comment : testList) {
			System.out.println(comment);
		}
		
	}
	
	@Test
	public void test3() {
		System.out.println("마음의소리!!");
		System.out.println(webtoonBo.readWebtoonId("마음의소리"));
	}
	
	@Test
	public void test4() {
		List<Webtoon> recommendWebtoonList = webtoonBo.getRecommendWebtoonList(20853);
		
		for(Webtoon webtoon : recommendWebtoonList) {
			System.out.println(webtoon.toString());
		}
	}

}
