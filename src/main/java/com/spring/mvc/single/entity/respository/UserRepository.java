package com.spring.mvc.single.entity.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.mvc.single.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
