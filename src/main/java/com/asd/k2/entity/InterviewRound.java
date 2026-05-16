package com.asd.k2.entity;

import java.math.BigDecimal;
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
@Table(name = "interview_round")
@SQLDelete(sql = "UPDATE interview_round SET deleted = 1 WHERE id = ?")
@SQLRestriction("deleted = 0")
public class InterviewRound {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "application_id", nullable = false)
	private Long applicationId;

	@Column(name = "round_no", nullable = false)
	private Integer roundNo;

	@Column(name = "round_type", length = 50)
	private String roundType;

	@Column(length = 100)
	private String interviewer;

	@Column(name = "interviewer_title", length = 100)
	private String interviewerTitle;

	@Column(name = "interview_method", length = 50)
	private String interviewMethod;

	@Column(name = "meeting_link", length = 500)
	private String meetingLink;

	@Column(name = "interview_time")
	private LocalDateTime interviewTime;

	@Column(name = "duration_minutes")
	private Integer durationMinutes;

	@Column(length = 50)
	private String result;

	private BigDecimal score;

	@Column(columnDefinition = "TEXT")
	private String summary;

	@Column(columnDefinition = "TEXT")
	private String feedback;

	@Column(name = "next_round_time")
	private LocalDateTime nextRoundTime;

	@Column(name = "created_at", insertable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", insertable = false, updatable = false)
	private LocalDateTime updatedAt;

	@Column(insertable = false, updatable = false)
	private Integer deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getRoundNo() {
		return roundNo;
	}

	public void setRoundNo(Integer roundNo) {
		this.roundNo = roundNo;
	}

	public String getRoundType() {
		return roundType;
	}

	public void setRoundType(String roundType) {
		this.roundType = roundType;
	}

	public String getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}

	public String getInterviewerTitle() {
		return interviewerTitle;
	}

	public void setInterviewerTitle(String interviewerTitle) {
		this.interviewerTitle = interviewerTitle;
	}

	public String getInterviewMethod() {
		return interviewMethod;
	}

	public void setInterviewMethod(String interviewMethod) {
		this.interviewMethod = interviewMethod;
	}

	public String getMeetingLink() {
		return meetingLink;
	}

	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}

	public LocalDateTime getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(LocalDateTime interviewTime) {
		this.interviewTime = interviewTime;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public LocalDateTime getNextRoundTime() {
		return nextRoundTime;
	}

	public void setNextRoundTime(LocalDateTime nextRoundTime) {
		this.nextRoundTime = nextRoundTime;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public Integer getDeleted() {
		return deleted;
	}
}
