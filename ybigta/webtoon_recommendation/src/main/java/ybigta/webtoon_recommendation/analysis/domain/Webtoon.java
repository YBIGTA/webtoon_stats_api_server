package ybigta.webtoon_recommendation.analysis.domain;

import java.util.Map;

public class Webtoon {

	private int titleId;
	private String title;
	private String author;
	private String genre;
	private String thumbnail;
	private double scoreParticipantAverage;
	private double heartsAverage;

	private Map<String, Double> featureVector;

	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public double getScoreParticipantAverage() {
		return scoreParticipantAverage;
	}

	public void setScoreParticipantAverage(double scoreParticipantAverage) {
		this.scoreParticipantAverage = scoreParticipantAverage;
	}

	public double getHeartsAverage() {
		return heartsAverage;
	}

	public void setHeartsAverage(double heartsAverage) {
		this.heartsAverage = heartsAverage;
	}

	public Map<String, Double> getFeatureVector() {
		return featureVector;
	}

	public void setFeatureVector(Map<String, Double> featureVector) {
		this.featureVector = featureVector;
	}

}
