package com.asd.k2.service;

import java.util.Optional;

import com.asd.k2.dto.SysUserSaveRequest;
import com.asd.k2.vo.PageResult;
import com.asd.k2.vo.SysUserVo;

public interface SysUserService {

	PageResult<SysUserVo> page(int page, int size);

	Optional<SysUserVo> getById(Long id);

	SysUserVo create(SysUserSaveRequest request);

	Optional<SysUserVo> update(Long id, SysUserSaveRequest request);

	boolean deleteById(Long id);
}
