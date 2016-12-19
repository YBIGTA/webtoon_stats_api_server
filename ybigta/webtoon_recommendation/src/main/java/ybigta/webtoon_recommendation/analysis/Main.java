package ybigta.webtoon_recommendation.analysis;

import java.util.List;

import ybigta.webtoon_recommendation.analysis.domain.Morpheme;
import ybigta.webtoon_recommendation.analysis.domain.Webtoon;
import ybigta.webtoon_recommendation.analysis.utils.CSVProcessor;
import ybigta.webtoon_recommendation.analysis.utils.WebtoonDAO;

public class Main {
	
	public static void main(String[] args) {

		//makeCSVFileForWordCloud();
		//testGetMorphemeCountByMorpheme();
		test();
	}
	
	public static void test() {
		String t = "episode,daily,comic";
		String t2 = "omnibus,fantasy";
		
		int simScore = 0;
		
		for(String token : t.split(",")) {
			for(String token2 : t2.split(",")) {
				
				System.out.println("token1: " + token);
				System.out.println("token2: " + token2);
				
				if(token.equals(token2)) {

					simScore++;
				}
			}
		}
		
		System.out.println("Sim: " + simScore);
	}

	public static void makeCSVFileForWordCloud() {

		WebtoonDAO dao = new WebtoonDAO();
		CSVProcessor csvProcessor = new CSVProcessor();

		List<Webtoon> webtoonList = dao.getWebtoonList();
		List<Morpheme> morphemeList = null;

		for (Webtoon webtoon : webtoonList) {
			System.out.println("Cur Processing: " + webtoon.getTitle());
			morphemeList = dao.getMorphemeCountByWebtoon(webtoon.getTitleId());
			csvProcessor.writeFile(webtoon.getTitle(), morphemeList);
		}
	}
	
	public static void makeSimilarity() {
		WebtoonDAO dao = new WebtoonDAO();
		
		
	}
	
	public static void testGetMorphemeCountByMorpheme() {
		WebtoonDAO dao = new WebtoonDAO();
		List<Morpheme> morphemeList = dao.getMorphemeCountByMorpheme("\"ㅋㅋㅋ\"");
		
		for(Morpheme morpheme : morphemeList) {
			System.out.println(morpheme);
		}
	}
}
