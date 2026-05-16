package com.asd.k2.entity;

import java.time.LocalDate;
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
@Table(name = "offer_info")
@SQLDelete(sql = "UPDATE offer_info SET deleted = 1 WHERE id = ?")
@SQLRestriction("deleted = 0")
public class OfferInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "application_id", nullable = false)
	private Long applicationId;

	@Column(name = "base_salary")
	private Integer baseSalary;

	@Column(name = "bonus_salary")
	private Integer bonusSalary;

	@Column(name = "stock_value")
	private Integer stockValue;

	@Column(name = "sign_bonus")
	private Integer signBonus;

	@Column(name = "other_benefits", columnDefinition = "TEXT")
	private String otherBenefits;

	@Column(name = "offer_date")
	private LocalDate offerDate;

	@Column(name = "deadline_date")
	private LocalDate deadlineDate;

	@Column(name = "join_date")
	private LocalDate joinDate;

	@Column(length = 50)
	private String status;

	@Column(columnDefinition = "TEXT")
	private String remark;

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

	public Integer getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Integer baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Integer getBonusSalary() {
		return bonusSalary;
	}

	public void setBonusSalary(Integer bonusSalary) {
		this.bonusSalary = bonusSalary;
	}

	public Integer getStockValue() {
		return stockValue;
	}

	public void setStockValue(Integer stockValue) {
		this.stockValue = stockValue;
	}

	public Integer getSignBonus() {
		return signBonus;
	}

	public void setSignBonus(Integer signBonus) {
		this.signBonus = signBonus;
	}

	public String getOtherBenefits() {
		return otherBenefits;
	}

	public void setOtherBenefits(String otherBenefits) {
		this.otherBenefits = otherBenefits;
	}

	public LocalDate getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(LocalDate offerDate) {
		this.offerDate = offerDate;
	}

	public LocalDate getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(LocalDate deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public Integer getDeleted() {
		return deleted;
	}
}
