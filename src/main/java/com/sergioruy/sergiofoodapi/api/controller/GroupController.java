package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.GroupInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.GroupModelAssembler;
import com.sergioruy.sergiofoodapi.domain.repository.GroupRepository;
import com.sergioruy.sergiofoodapi.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupInputDisassembler groupInputDisassembler;

    @Autowired
    private GroupModelAssembler groupModelAssembler;
}
