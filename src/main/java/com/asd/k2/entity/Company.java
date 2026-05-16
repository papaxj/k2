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
@Table(name = "company")
@SQLDelete(sql = "UPDATE company SET deleted = 1 WHERE id = ?")
@SQLRestriction("deleted = 0")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(length = 100)
	private String industry;

	@Column(length = 255)
	private String website;

	@Column(length = 100)
	private String city;

	@Column(name = "company_size", length = 50)
	private String companySize;

	@Column(name = "financing_stage", length = 50)
	private String financingStage;

	@Column(length = 255)
	private String address;

	@Column(name = "hr_name", length = 100)
	private String hrName;

	@Column(name = "hr_contact", length = 100)
	private String hrContact;

	@Column(columnDefinition = "TEXT")
	private String remark;

	private Integer status;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getFinancingStage() {
		return financingStage;
	}

	public void setFinancingStage(String financingStage) {
		this.financingStage = financingStage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHrName() {
		return hrName;
	}

	public void setHrName(String hrName) {
		this.hrName = hrName;
	}

	public String getHrContact() {
		return hrContact;
	}

	public void setHrContact(String hrContact) {
		this.hrContact = hrContact;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
