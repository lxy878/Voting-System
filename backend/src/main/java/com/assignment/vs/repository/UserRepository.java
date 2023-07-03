package com.assignment.vs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.vs.domain.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{
    public Optional<UserInfo> findByName(String name);
}
