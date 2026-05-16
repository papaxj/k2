package com.asd.k2.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asd.k2.common.Pageables;
import com.asd.k2.dto.InterviewQuestionSaveRequest;
import com.asd.k2.entity.InterviewQuestion;
import com.asd.k2.mapper.InterviewQuestionRepository;
import com.asd.k2.service.InterviewQuestionService;
import com.asd.k2.vo.InterviewQuestionVo;
import com.asd.k2.vo.PageResult;

@Service
@Transactional(readOnly = true)
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

	private final InterviewQuestionRepository interviewQuestionRepository;

	public InterviewQuestionServiceImpl(InterviewQuestionRepository interviewQuestionRepository) {
		this.interviewQuestionRepository = interviewQuestionRepository;
	}

	@Override
	public PageResult<InterviewQuestionVo> page(int page, int size, Long roundId) {
		Pageable pageable = Pageables.of(page, size);
		Page<InterviewQuestion> result = roundId != null
				? interviewQuestionRepository.findByRoundId(roundId, pageable)
				: interviewQuestionRepository.findAll(pageable);
		return PageResult.of(result, page, this::toVo);
	}

	@Override
	public Optional<InterviewQuestionVo> getById(Long id) {
		return interviewQuestionRepository.findById(id).map(this::toVo);
	}

	@Override
	@Transactional
	public InterviewQuestionVo create(InterviewQuestionSaveRequest request) {
		InterviewQuestion entity = new InterviewQuestion();
		applyRequest(entity, request);
		return toVo(interviewQuestionRepository.save(entity));
	}

	@Override
	@Transactional
	public Optional<InterviewQuestionVo> update(Long id, InterviewQuestionSaveRequest request) {
		return interviewQuestionRepository.findById(id).map(entity -> {
			applyRequest(entity, request);
			return toVo(interviewQuestionRepository.save(entity));
		});
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if (!interviewQuestionRepository.existsById(id)) {
			return false;
		}
		interviewQuestionRepository.deleteById(id);
		return true;
	}

	private void applyRequest(InterviewQuestion entity, InterviewQuestionSaveRequest request) {
		entity.setRoundId(request.roundId());
		entity.setCategory(request.category());
		entity.setQuestion(request.question());
		entity.setMyAnswer(request.myAnswer());
		entity.setCorrectAnswer(request.correctAnswer());
		entity.setDifficultyLevel(request.difficultyLevel() != null ? request.difficultyLevel() : 2);
		entity.setIsAnsweredCorrectly(request.isAnsweredCorrectly() != null ? request.isAnsweredCorrectly() : 0);
		entity.setRemark(request.remark());
	}

	private InterviewQuestionVo toVo(InterviewQuestion entity) {
		return new InterviewQuestionVo(
				entity.getId(),
				entity.getRoundId(),
				entity.getCategory(),
				entity.getQuestion(),
				entity.getMyAnswer(),
				entity.getCorrectAnswer(),
				entity.getDifficultyLevel(),
				entity.getIsAnsweredCorrectly(),
				entity.getRemark(),
				entity.getCreatedAt());
	}
}
