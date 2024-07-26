package com.nagarro.customerauthorization.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.customerauthorization.model.UserEntity;

@Repository
public interface UserRepositry extends JpaRepository<UserEntity, String> {

}
