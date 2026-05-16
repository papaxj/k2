package com.asd.k2.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.asd.k2.common.Pageables;
import com.asd.k2.dto.SysUserSaveRequest;
import com.asd.k2.entity.SysUser;
import com.asd.k2.mapper.SysUserRepository;
import com.asd.k2.service.SysUserService;
import com.asd.k2.vo.PageResult;
import com.asd.k2.vo.SysUserVo;

@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl implements SysUserService {

	private final SysUserRepository sysUserRepository;

	public SysUserServiceImpl(SysUserRepository sysUserRepository) {
		this.sysUserRepository = sysUserRepository;
	}

	@Override
	public PageResult<SysUserVo> page(int page, int size) {
		Pageable pageable = Pageables.of(page, size);
		Page<SysUser> result = sysUserRepository.findAll(pageable);
		return PageResult.of(result, page, this::toVo);
	}

	@Override
	public Optional<SysUserVo> getById(Long id) {
		return sysUserRepository.findById(id).map(this::toVo);
	}

	@Override
	@Transactional
	public SysUserVo create(SysUserSaveRequest request) {
		if (!StringUtils.hasText(request.password())) {
			throw new IllegalArgumentException("密码不能为空");
		}
		SysUser entity = new SysUser();
		applyRequest(entity, request, true);
		return toVo(sysUserRepository.save(entity));
	}

	@Override
	@Transactional
	public Optional<SysUserVo> update(Long id, SysUserSaveRequest request) {
		return sysUserRepository.findById(id).map(entity -> {
			applyRequest(entity, request, false);
			return toVo(sysUserRepository.save(entity));
		});
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if (!sysUserRepository.existsById(id)) {
			return false;
		}
		sysUserRepository.deleteById(id);
		return true;
	}

	private void applyRequest(SysUser entity, SysUserSaveRequest request, boolean isCreate) {
		entity.setUsername(request.username());
		if (StringUtils.hasText(request.password())) {
			entity.setPassword(request.password());
		} else if (isCreate) {
			throw new IllegalArgumentException("密码不能为空");
		}
		entity.setNickname(request.nickname());
		entity.setEmail(request.email());
		entity.setAvatar(request.avatar());
		entity.setPhone(request.phone());
		entity.setStatus(request.status() != null ? request.status() : 1);
		entity.setLastLoginTime(request.lastLoginTime());
	}

	private SysUserVo toVo(SysUser entity) {
		return new SysUserVo(
				entity.getId(),
				entity.getUsername(),
				entity.getNickname(),
				entity.getEmail(),
				entity.getAvatar(),
				entity.getPhone(),
				entity.getStatus(),
				entity.getLastLoginTime(),
				entity.getCreatedAt(),
				entity.getUpdatedAt());
	}
}
