package com.user.reponsitory;


import com.user.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Hangqi Yu
 * 2019-10-01 21:42
 */
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAll();

    User findByEmail(String email);

    List<User> findAllByEmail(String email);

    User findByEmailAndPassword(String email,String password);

}
