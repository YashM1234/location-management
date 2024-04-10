package com.application.locationmanagement.repository;

import com.application.locationmanagement.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

    public UserEntity findByEmailAndPassword(String email, String password);

    public UserEntity findByEmail(String email);
}
