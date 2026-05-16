package com.asd.k2.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asd.k2.common.Pageables;
import com.asd.k2.dto.MyUserSaveRequest;
import com.asd.k2.entity.MyUser;
import com.asd.k2.mapper.MyUserRepository;
import com.asd.k2.service.MyUserService;
import com.asd.k2.vo.MyUserVo;
import com.asd.k2.vo.PageResult;

@Service
@Transactional(readOnly = true)
public class MyUserServiceImpl implements MyUserService {

	private final MyUserRepository myUserRepository;

	public MyUserServiceImpl(MyUserRepository myUserRepository) {
		this.myUserRepository = myUserRepository;
	}

	@Override
	public PageResult<MyUserVo> page(int page, int size) {
		Pageable pageable = Pageables.of(page, size);
		Page<MyUser> result = myUserRepository.findAll(pageable);
		return PageResult.of(result, page, this::toVo);
	}

	@Override
	public Optional<MyUserVo> getById(Integer id) {
		return myUserRepository.findById(id).map(this::toVo);
	}

	@Override
	@Transactional
	public MyUserVo create(MyUserSaveRequest request) {
		MyUser entity = new MyUser();
		applyRequest(entity, request);
		return toVo(myUserRepository.save(entity));
	}

	@Override
	@Transactional
	public Optional<MyUserVo> update(Integer id, MyUserSaveRequest request) {
		return myUserRepository.findById(id).map(entity -> {
			applyRequest(entity, request);
			return toVo(myUserRepository.save(entity));
		});
	}

	@Override
	@Transactional
	public boolean deleteById(Integer id) {
		if (!myUserRepository.existsById(id)) {
			return false;
		}
		myUserRepository.deleteById(id);
		return true;
	}

	private void applyRequest(MyUser entity, MyUserSaveRequest request) {
		entity.setName(request.name());
		entity.setAsName(request.asName());
		entity.setBirthday(request.birthday());
		entity.setSex(request.sex());
		entity.setEmail(request.email());
		entity.setAddress(request.address());
		entity.setAge(request.age());
	}

	private MyUserVo toVo(MyUser entity) {
		return new MyUserVo(
				entity.getId(),
				entity.getName(),
				entity.getAsName(),
				entity.getBirthday(),
				entity.getSex(),
				entity.getEmail(),
				entity.getAddress(),
				entity.getAge());
	}
}
