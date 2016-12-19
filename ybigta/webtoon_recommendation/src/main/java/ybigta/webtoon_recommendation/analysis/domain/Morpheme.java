package ybigta.webtoon_recommendation.analysis.domain;

public class Morpheme {

	private String morpheme;
	private int morphemeCount;
	private String morphemeTag;
	private int titleId;

	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public String getMorpheme() {
		return morpheme;
	}

	public void setMorpheme(String morpheme) {
		this.morpheme = morpheme;
	}

	public int getMorphemeCount() {
		return morphemeCount;
	}

	public void setMorphemeCount(int morphemeCount) {
		this.morphemeCount = morphemeCount;
	}

	public String getMorphemeTag() {
		return morphemeTag;
	}

	public void setMorphemeTag(String morphemeTag) {
		this.morphemeTag = morphemeTag;
	}
	
	@Override
	public String toString() {
		return "Morpheme [morpheme=" + morpheme + ", morphemeCount=" + morphemeCount + ", morphemeTag=" + morphemeTag
				+ ", titleId=" + titleId + "]";
	}

}
