package ybigta.webtoon_recommendation.analysis.domain;

public class Similarity {

	private String webtoon1Name;
	private String webtoon2Name;
	private double similarity;

	public String getWebtoon1Name() {
		return webtoon1Name;
	}

	public void setWebtoon1Name(String webtoon1Name) {
		this.webtoon1Name = webtoon1Name;
	}

	public String getWebtoon2Name() {
		return webtoon2Name;
	}

	public void setWebtoon2Name(String webtoon2Name) {
		this.webtoon2Name = webtoon2Name;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

}
