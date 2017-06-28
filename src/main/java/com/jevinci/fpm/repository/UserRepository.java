package com.jevinci.fpm.repository;

import com.jevinci.fpm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */
public interface UserRepository extends JpaRepository<User, String>, UserCustomRepository{
    User findBySocialId(String socialId);
}
