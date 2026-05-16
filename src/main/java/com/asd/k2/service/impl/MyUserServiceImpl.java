package com.asd.k2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asd.k2.dto.MyUserSaveRequest;
import com.asd.k2.entity.MyUser;
import com.asd.k2.mapper.MyUserRepository;
import com.asd.k2.service.MyUserService;
import com.asd.k2.vo.MyUserVo;

@Service
@Transactional(readOnly = true)
public class MyUserServiceImpl implements MyUserService {

	private final MyUserRepository myUserRepository;

	public MyUserServiceImpl(MyUserRepository myUserRepository) {
		this.myUserRepository = myUserRepository;
	}

	@Override
	public List<MyUserVo> listAll() {
		return myUserRepository.findAll().stream().map(this::toVo).toList();
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
