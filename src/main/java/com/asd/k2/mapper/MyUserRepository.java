package com.asd.k2.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asd.k2.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
}
