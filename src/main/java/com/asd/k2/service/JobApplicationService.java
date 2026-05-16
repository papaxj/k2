package com.asd.k2.service;

import java.util.Optional;

import com.asd.k2.dto.JobApplicationSaveRequest;
import com.asd.k2.vo.JobApplicationVo;
import com.asd.k2.vo.PageResult;

public interface JobApplicationService {

	PageResult<JobApplicationVo> page(int page, int size, Long userId, Long companyId);

	Optional<JobApplicationVo> getById(Long id);

	JobApplicationVo create(JobApplicationSaveRequest request);

	Optional<JobApplicationVo> update(Long id, JobApplicationSaveRequest request);

	boolean deleteById(Long id);
}
