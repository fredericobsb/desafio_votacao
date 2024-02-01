package com.sicred.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicred.sistema.entidades.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
