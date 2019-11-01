package com.example.group.repository;



import com.example.group.dataobject.Scorhgroup;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Created by Hangqi Yu
 * 2019-10-01 21:42
 */

public interface GroupRepository extends JpaRepository<Scorhgroup, String> {

    List<Scorhgroup> findAll();
}
