package com.asd.k2.service;

import java.util.Optional;

import com.asd.k2.dto.InterviewRoundSaveRequest;
import com.asd.k2.vo.InterviewRoundVo;
import com.asd.k2.vo.PageResult;

public interface InterviewRoundService {

	PageResult<InterviewRoundVo> page(int page, int size, Long applicationId);

	Optional<InterviewRoundVo> getById(Long id);

	InterviewRoundVo create(InterviewRoundSaveRequest request);

	Optional<InterviewRoundVo> update(Long id, InterviewRoundSaveRequest request);

	boolean deleteById(Long id);
}
