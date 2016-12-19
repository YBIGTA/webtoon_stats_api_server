package ybigta.webtoon_recommendation.domain;

import java.util.Date;

public class Comment {

	private int titleId;
	private int episodeNo;
	private int commentNo;
	private String comment;
	private int commentLength;
	private Date postTime;
	private int bestComment;
	private int likes;
	private int hates;

	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public int getEpisodeNo() {
		return episodeNo;
	}

	public void setEpisodeNo(int episodeNo) {
		this.episodeNo = episodeNo;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCommentLength() {
		return commentLength;
	}

	public void setCommentLength(int commentLength) {
		this.commentLength = commentLength;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public int getBestComment() {
		return bestComment;
	}

	public void setBestComment(int bestComment) {
		this.bestComment = bestComment;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getHates() {
		return hates;
	}

	public void setHates(int hates) {
		this.hates = hates;
	}

	@Override
	public String toString() {
		return "Comment [titleId=" + titleId + ", episodeNo=" + episodeNo + ", commentNo=" + commentNo + ", comment="
				+ comment + ", commentLength=" + commentLength + ", postTime=" + postTime + ", bestComment="
				+ bestComment + ", likes=" + likes + ", hates=" + hates + "]";
	}

}
