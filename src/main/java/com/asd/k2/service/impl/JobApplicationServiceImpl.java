package com.asd.k2.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asd.k2.common.Pageables;
import com.asd.k2.dto.JobApplicationSaveRequest;
import com.asd.k2.entity.JobApplication;
import com.asd.k2.mapper.JobApplicationRepository;
import com.asd.k2.service.JobApplicationService;
import com.asd.k2.vo.JobApplicationVo;
import com.asd.k2.vo.PageResult;

@Service
@Transactional(readOnly = true)
public class JobApplicationServiceImpl implements JobApplicationService {

	private final JobApplicationRepository jobApplicationRepository;

	public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository) {
		this.jobApplicationRepository = jobApplicationRepository;
	}

	@Override
	public PageResult<JobApplicationVo> page(int page, int size, Long userId, Long companyId) {
		Pageable pageable = Pageables.of(page, size);
		Page<JobApplication> result;
		if (userId != null && companyId != null) {
			result = jobApplicationRepository.findByUserIdAndCompanyId(userId, companyId, pageable);
		} else if (userId != null) {
			result = jobApplicationRepository.findByUserId(userId, pageable);
		} else if (companyId != null) {
			result = jobApplicationRepository.findByCompanyId(companyId, pageable);
		} else {
			result = jobApplicationRepository.findAll(pageable);
		}
		return PageResult.of(result, page, this::toVo);
	}

	@Override
	public Optional<JobApplicationVo> getById(Long id) {
		return jobApplicationRepository.findById(id).map(this::toVo);
	}

	@Override
	@Transactional
	public JobApplicationVo create(JobApplicationSaveRequest request) {
		JobApplication entity = new JobApplication();
		applyRequest(entity, request);
		return toVo(jobApplicationRepository.save(entity));
	}

	@Override
	@Transactional
	public Optional<JobApplicationVo> update(Long id, JobApplicationSaveRequest request) {
		return jobApplicationRepository.findById(id).map(entity -> {
			applyRequest(entity, request);
			return toVo(jobApplicationRepository.save(entity));
		});
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if (!jobApplicationRepository.existsById(id)) {
			return false;
		}
		jobApplicationRepository.deleteById(id);
		return true;
	}

	private void applyRequest(JobApplication entity, JobApplicationSaveRequest request) {
		entity.setUserId(request.userId());
		entity.setCompanyId(request.companyId());
		entity.setPositionName(request.positionName());
		entity.setDepartment(request.department());
		entity.setEmploymentType(request.employmentType());
		entity.setWorkCity(request.workCity());
		entity.setSalaryMin(request.salaryMin());
		entity.setSalaryMax(request.salaryMax());
		entity.setSalaryMonths(request.salaryMonths() != null ? request.salaryMonths() : 12);
		entity.setJobDesc(request.jobDesc());
		entity.setSource(request.source());
		entity.setSourceLink(request.sourceLink());
		entity.setApplyDate(request.applyDate());
		entity.setCurrentStage(request.currentStage() != null ? request.currentStage() : "APPLIED");
		entity.setStatus(request.status() != null ? request.status() : "PROCESSING");
		entity.setPriorityLevel(request.priorityLevel() != null ? request.priorityLevel() : 2);
		entity.setExpectedSalary(request.expectedSalary());
		entity.setRemark(request.remark());
	}

	private JobApplicationVo toVo(JobApplication entity) {
		return new JobApplicationVo(
				entity.getId(),
				entity.getUserId(),
				entity.getCompanyId(),
				entity.getPositionName(),
				entity.getDepartment(),
				entity.getEmploymentType(),
				entity.getWorkCity(),
				entity.getSalaryMin(),
				entity.getSalaryMax(),
				entity.getSalaryMonths(),
				entity.getJobDesc(),
				entity.getSource(),
				entity.getSourceLink(),
				entity.getApplyDate(),
				entity.getCurrentStage(),
				entity.getStatus(),
				entity.getPriorityLevel(),
				entity.getExpectedSalary(),
				entity.getRemark(),
				entity.getCreatedAt(),
				entity.getUpdatedAt());
	}
}
