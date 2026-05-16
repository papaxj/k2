package com.asd.k2.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asd.k2.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
}
