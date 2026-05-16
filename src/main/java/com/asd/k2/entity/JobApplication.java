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
@Table(name = "job_application")
@SQLDelete(sql = "UPDATE job_application SET deleted = 1 WHERE id = ?")
@SQLRestriction("deleted = 0")
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "company_id", nullable = false)
	private Long companyId;

	@Column(name = "position_name", nullable = false, length = 100)
	private String positionName;

	@Column(length = 100)
	private String department;

	@Column(name = "employment_type", length = 50)
	private String employmentType;

	@Column(name = "work_city", length = 100)
	private String workCity;

	@Column(name = "salary_min")
	private Integer salaryMin;

	@Column(name = "salary_max")
	private Integer salaryMax;

	@Column(name = "salary_months")
	private Integer salaryMonths;

	@Column(name = "job_desc", columnDefinition = "TEXT")
	private String jobDesc;

	@Column(length = 50)
	private String source;

	@Column(name = "source_link", length = 500)
	private String sourceLink;

	@Column(name = "apply_date")
	private LocalDate applyDate;

	@Column(name = "current_stage", length = 50)
	private String currentStage;

	@Column(length = 50)
	private String status;

	@Column(name = "priority_level")
	private Integer priorityLevel;

	@Column(name = "expected_salary")
	private Integer expectedSalary;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public Integer getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(Integer salaryMin) {
		this.salaryMin = salaryMin;
	}

	public Integer getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(Integer salaryMax) {
		this.salaryMax = salaryMax;
	}

	public Integer getSalaryMonths() {
		return salaryMonths;
	}

	public void setSalaryMonths(Integer salaryMonths) {
		this.salaryMonths = salaryMonths;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceLink() {
		return sourceLink;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	public LocalDate getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
	}

	public String getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(String currentStage) {
		this.currentStage = currentStage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(Integer priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public Integer getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(Integer expectedSalary) {
		this.expectedSalary = expectedSalary;
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
