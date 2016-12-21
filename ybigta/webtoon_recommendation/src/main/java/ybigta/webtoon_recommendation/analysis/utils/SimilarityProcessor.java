package ybigta.webtoon_recommendation.analysis.utils;

import java.util.ArrayList;
import java.util.List;

import ybigta.webtoon_recommendation.analysis.domain.Webtoon;
import ybigta.webtoon_recommendation.analysis.domain.Similarity;

public class SimilarityProcessor {

	/**
	 * input으로 webtoon list, cosine sim을 구할 수 있는 두 개의 map을 받아야 할 듯
	 * 
	 * @return
	 */
	public Double getSimilarityBetweenWebtoon(Webtoon webtoon1, Webtoon webtoon2) {

		// weight
		double w1 = 20.2; // weight for cosine similarity
		double w2 = 1.0; // weight for author similarity
		double w3 = 6.0; // weight for genre similarity
		double w4 = 0.00006; // weight for score participant average
		double w5 = 0.00015; // weight for heart average

		// 1. Cosine similarity를 구할 부분
		CosineSimilarity cosine = new CosineSimilarity();
		double cosineSimilarity = cosine.cosineSimilarity(webtoon1.getFeatureVector(), webtoon2.getFeatureVector());
		// 2. 작가가 같은지 여부 확인
		int isAuthorEqual = webtoon1.getAuthor().equals(webtoon2.getAuthor()) ? 1 : 0;

		// 3. 장르 점수 얻기
		int isGenreEqual = genreSimilarity(webtoon1.getGenre(), webtoon2.getGenre());

		// 
		double scoreParticipanAverage = webtoon2.getScoreParticipantAverage();
		double heartsAverage = webtoon2.getHeartsAverage();

		return w1 * cosineSimilarity + w2 * isAuthorEqual + w3 * isGenreEqual + w4 * scoreParticipanAverage
				+ w5 * heartsAverage;
	}

	private int genreSimilarity(String genre1, String genre2) {

		int similarityScore = 0;

		for (String token : genre1.split(",")) {
			for (String token2 : genre2.split(",")) {

				if (token.equals(token2)) {
					similarityScore++;
				}
			}
		}
		return similarityScore;
	}

	// make similarity list
	public List<Similarity> getSimilarityList(List<Webtoon> webtoonList) {

		List<Similarity> similarityList = new ArrayList<>();

		double similarityScore;
		Similarity similarity;
		Webtoon webtoon1, webtoon2;

		for (int i = 0; i < webtoonList.size(); i++) {
			for (int j = 0; j < webtoonList.size(); j++) {

				if (i == j) {
					continue;
				}

				webtoon1 = webtoonList.get(i);
				webtoon2 = webtoonList.get(j);

				similarityScore = getSimilarityBetweenWebtoon(webtoon1, webtoon2);

				similarity = new Similarity();
				similarity.setWebtoon1Id(webtoon1.getTitleId());
				similarity.setWebtoon2Id(webtoon2.getTitleId());
				similarity.setSimilarity(similarityScore);

				similarityList.add(similarity);
			}
		}

		return similarityList;
	}
	
}
