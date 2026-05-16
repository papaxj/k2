package com.asd.k2.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asd.k2.common.Pageables;
import com.asd.k2.dto.InterviewRoundSaveRequest;
import com.asd.k2.entity.InterviewRound;
import com.asd.k2.mapper.InterviewRoundRepository;
import com.asd.k2.service.InterviewRoundService;
import com.asd.k2.vo.InterviewRoundVo;
import com.asd.k2.vo.PageResult;

@Service
@Transactional(readOnly = true)
public class InterviewRoundServiceImpl implements InterviewRoundService {

	private final InterviewRoundRepository interviewRoundRepository;

	public InterviewRoundServiceImpl(InterviewRoundRepository interviewRoundRepository) {
		this.interviewRoundRepository = interviewRoundRepository;
	}

	@Override
	public PageResult<InterviewRoundVo> page(int page, int size, Long applicationId) {
		Sort sort = applicationId != null
				? Sort.by(Sort.Direction.ASC, "roundNo")
				: Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = Pageables.of(page, size, sort);
		Page<InterviewRound> result = applicationId != null
				? interviewRoundRepository.findByApplicationId(applicationId, pageable)
				: interviewRoundRepository.findAll(pageable);
		return PageResult.of(result, page, this::toVo);
	}

	@Override
	public Optional<InterviewRoundVo> getById(Long id) {
		return interviewRoundRepository.findById(id).map(this::toVo);
	}

	@Override
	@Transactional
	public InterviewRoundVo create(InterviewRoundSaveRequest request) {
		InterviewRound entity = new InterviewRound();
		applyRequest(entity, request);
		return toVo(interviewRoundRepository.save(entity));
	}

	@Override
	@Transactional
	public Optional<InterviewRoundVo> update(Long id, InterviewRoundSaveRequest request) {
		return interviewRoundRepository.findById(id).map(entity -> {
			applyRequest(entity, request);
			return toVo(interviewRoundRepository.save(entity));
		});
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if (!interviewRoundRepository.existsById(id)) {
			return false;
		}
		interviewRoundRepository.deleteById(id);
		return true;
	}

	private void applyRequest(InterviewRound entity, InterviewRoundSaveRequest request) {
		entity.setApplicationId(request.applicationId());
		entity.setRoundNo(request.roundNo());
		entity.setRoundType(request.roundType());
		entity.setInterviewer(request.interviewer());
		entity.setInterviewerTitle(request.interviewerTitle());
		entity.setInterviewMethod(request.interviewMethod());
		entity.setMeetingLink(request.meetingLink());
		entity.setInterviewTime(request.interviewTime());
		entity.setDurationMinutes(request.durationMinutes());
		entity.setResult(request.result() != null ? request.result() : "PENDING");
		entity.setScore(request.score());
		entity.setSummary(request.summary());
		entity.setFeedback(request.feedback());
		entity.setNextRoundTime(request.nextRoundTime());
	}

	private InterviewRoundVo toVo(InterviewRound entity) {
		return new InterviewRoundVo(
				entity.getId(),
				entity.getApplicationId(),
				entity.getRoundNo(),
				entity.getRoundType(),
				entity.getInterviewer(),
				entity.getInterviewerTitle(),
				entity.getInterviewMethod(),
				entity.getMeetingLink(),
				entity.getInterviewTime(),
				entity.getDurationMinutes(),
				entity.getResult(),
				entity.getScore(),
				entity.getSummary(),
				entity.getFeedback(),
				entity.getNextRoundTime(),
				entity.getCreatedAt(),
				entity.getUpdatedAt());
	}
}
