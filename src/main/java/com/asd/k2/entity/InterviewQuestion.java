package com.asd.k2.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "interview_question")
@SQLDelete(sql = "UPDATE interview_question SET deleted = 1 WHERE id = ?")
@SQLRestriction("deleted = 0")
public class InterviewQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "round_id", nullable = false)
	private Long roundId;

	@Column(length = 50)
	private String category;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String question;

	@Column(name = "my_answer", columnDefinition = "TEXT")
	private String myAnswer;

	@Column(name = "correct_answer", columnDefinition = "TEXT")
	private String correctAnswer;

	@Column(name = "difficulty_level")
	private Integer difficultyLevel;

	@Column(name = "is_answered_correctly")
	private Integer isAnsweredCorrectly;

	@Column(columnDefinition = "TEXT")
	private String remark;

	@Column(name = "created_at", insertable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(insertable = false, updatable = false)
	private Integer deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoundId() {
		return roundId;
	}

	public void setRoundId(Long roundId) {
		this.roundId = roundId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getMyAnswer() {
		return myAnswer;
	}

	public void setMyAnswer(String myAnswer) {
		this.myAnswer = myAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Integer getIsAnsweredCorrectly() {
		return isAnsweredCorrectly;
	}

	public void setIsAnsweredCorrectly(Integer isAnsweredCorrectly) {
		this.isAnsweredCorrectly = isAnsweredCorrectly;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Integer getDeleted() {
		return deleted;
	}
}
