package ybigta.webtoon_recommendation.analysis.domain;

public class Similarity {

	private int webtoon1Id;
	private int webtoon2Id;
	private double similarity;

	public int getWebtoon1Id() {
		return webtoon1Id;
	}

	public void setWebtoon1Id(int webtoon1Id) {
		this.webtoon1Id = webtoon1Id;
	}

	public int getWebtoon2Id() {
		return webtoon2Id;
	}

	public void setWebtoon2Id(int webtoon2Id) {
		this.webtoon2Id = webtoon2Id;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	@Override
	public String toString() {
		return "Similarity [webtoon1Id=" + webtoon1Id + ", webtoon2Id=" + webtoon2Id + ", similarity=" + similarity
				+ "]";
	}

}
