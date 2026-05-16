package com.asd.k2.service;

import java.util.Optional;

import com.asd.k2.dto.MyUserSaveRequest;
import com.asd.k2.vo.MyUserVo;
import com.asd.k2.vo.PageResult;

public interface MyUserService {

	PageResult<MyUserVo> page(int page, int size);

	Optional<MyUserVo> getById(Integer id);

	MyUserVo create(MyUserSaveRequest request);

	Optional<MyUserVo> update(Integer id, MyUserSaveRequest request);

	boolean deleteById(Integer id);
}
