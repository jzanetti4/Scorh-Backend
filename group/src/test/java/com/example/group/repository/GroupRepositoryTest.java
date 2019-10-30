package com.example.group.repository;

import com.example.group.GroupApplicationTests;
import com.example.group.dataobject.Scorhgroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;


@Component
public class GroupRepositoryTest extends GroupApplicationTests {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void save(){
        Scorhgroup group=new Scorhgroup();
        group.setCreator("1");
        group.setGroupName("1");
        group.setGroupCategory("1");
        group.setCreateTime(new Timestamp(new Date().getTime()));
        System.out.println(group);
        groupRepository.save(group);
    }
}