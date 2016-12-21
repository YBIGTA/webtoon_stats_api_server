package ybigta.webtoon_recommendation.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ybigta.webtoon_recommendation.analysis.domain.Morpheme;
import ybigta.webtoon_recommendation.analysis.domain.Similarity;
import ybigta.webtoon_recommendation.analysis.domain.Webtoon;
import ybigta.webtoon_recommendation.analysis.utils.CSVProcessor;
import ybigta.webtoon_recommendation.analysis.utils.CosineSimilarity;
import ybigta.webtoon_recommendation.analysis.utils.SimilarityProcessor;
import ybigta.webtoon_recommendation.analysis.utils.WebtoonDAO;

public class Main {

	public static void main(String[] args) {

		// makeCSVFileForWordCloud();
		// testGetMorphemeCountByMorpheme();
		// test();
		// callProcedure();

		//testStatProcess();

		// testCosineSim();
		
		makeSimilarity();
	}

	public static void testCosineSim() {
		WebtoonDAO dao = new WebtoonDAO();
		List<Webtoon> webtoonList = dao.getWebtoonList();

		Map<String, Double> featureVector1 = new HashMap<>();
		featureVector1.put("A", 100.0);
		featureVector1.put("B", 200.0);

		Map<String, Double> featureVector2 = new HashMap<>();
		featureVector2.put("A", 200.0);
		featureVector2.put("B", 300.0);

		Webtoon webtoon1 = webtoonList.get(0);
		Webtoon webtoon2 = webtoonList.get(1);

		CosineSimilarity cosine = new CosineSimilarity();

		System.out.println(cosine.cosineSimilarity(featureVector2, featureVector1));

		webtoon1.setFeatureVector(featureVector1);
		webtoon2.setFeatureVector(featureVector2);

		SimilarityProcessor simProcessor = new SimilarityProcessor();
		System.out.println(simProcessor.getSimilarityBetweenWebtoon(webtoon1, webtoon2));

	}

	public static void testStatProcess() {
		WebtoonDAO dao = new WebtoonDAO();

		List<Webtoon> webtoonList = dao.getWebtoonList();

		SimilarityProcessor simProcessor = new SimilarityProcessor();
		List<Similarity> similarityList = simProcessor.getSimilarityList(webtoonList);

		for (Similarity sim : similarityList) {
			System.out.println(sim.getSimilarity());
		}

	}

	public static void callProcedure() {
		WebtoonDAO dao = new WebtoonDAO();
		List<Webtoon> webtoonList = dao.getWebtoonList();
		System.out.println("webtoonList size: " + webtoonList.size());
		for (Webtoon webtoon : webtoonList) {
			System.out.println("Cur Processing: " + webtoon.getTitle());
			// dao.callProcedure1(webtoon.getTitleId());
			dao.callProcedure2(webtoon.getTitleId());
		}
	}

	public static void test() {
		WebtoonDAO dao = new WebtoonDAO();
		List<Webtoon> webtoonList = dao.getWebtoonList();

		for (Webtoon webtoon : webtoonList) {
			System.out.println(webtoon.getFeatureVector());
		}
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

		List<Webtoon> webtoonList = dao.getWebtoonList();
		
		//System.out.println(webtoonList.size());

		SimilarityProcessor simProcessor = new SimilarityProcessor();
		List<Similarity> similarityList = simProcessor.getSimilarityList(webtoonList);


		//System.out.println(similarityList.size());
		dao.insertSimilarity(similarityList);
	}

	public static void testGetMorphemeCountByMorpheme() {
		WebtoonDAO dao = new WebtoonDAO();
		List<Morpheme> morphemeList = dao.getMorphemeCountByMorpheme("\"ㅋㅋㅋ\"");

		for (Morpheme morpheme : morphemeList) {
			System.out.println(morpheme);
		}
	}
}
