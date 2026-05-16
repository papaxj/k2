package com.asd.k2.service;

import java.util.Optional;

import com.asd.k2.dto.InterviewQuestionSaveRequest;
import com.asd.k2.vo.InterviewQuestionVo;
import com.asd.k2.vo.PageResult;

public interface InterviewQuestionService {

	PageResult<InterviewQuestionVo> page(int page, int size, Long roundId);

	Optional<InterviewQuestionVo> getById(Long id);

	InterviewQuestionVo create(InterviewQuestionSaveRequest request);

	Optional<InterviewQuestionVo> update(Long id, InterviewQuestionSaveRequest request);

	boolean deleteById(Long id);
}
