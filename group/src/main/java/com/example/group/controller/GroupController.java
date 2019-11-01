package com.example.group.controller;


import com.example.group.dataobject.Scorhgroup;
import com.example.group.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;
    @GetMapping("/findAllGroup")
    public List<Scorhgroup> findAllGroup(){
        return groupRepository.findAll();
    }
}
