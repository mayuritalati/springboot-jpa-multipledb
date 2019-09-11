package com.mayuri.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayuri.user.data.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
