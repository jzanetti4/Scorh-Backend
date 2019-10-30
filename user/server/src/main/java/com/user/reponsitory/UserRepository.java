package com.user.reponsitory;


import com.user.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 廖师兄
 * 2018-03-04 21:42
 */
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAll();

    User findByEmail(String email);

    List<User> findAllByEmail(String email);

    User findByEmailAndPassword(String email,String password);
}
