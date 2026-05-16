package com.asd.k2.service;

import java.util.List;
import java.util.Optional;

import com.asd.k2.dto.MyUserSaveRequest;
import com.asd.k2.vo.MyUserVo;

public interface MyUserService {

	List<MyUserVo> listAll();

	Optional<MyUserVo> getById(Integer id);

	MyUserVo create(MyUserSaveRequest request);

	Optional<MyUserVo> update(Integer id, MyUserSaveRequest request);

	boolean deleteById(Integer id);
}
