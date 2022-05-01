package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.GroupInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.GroupModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.GroupModel;
import com.sergioruy.sergiofoodapi.api.model.input.GroupInput;
import com.sergioruy.sergiofoodapi.domain.model.Group;
import com.sergioruy.sergiofoodapi.domain.repository.GroupRepository;
import com.sergioruy.sergiofoodapi.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public List<GroupModel> list() {
        List<Group> allGroups = groupRepository.findAll();
        return groupModelAssembler.toCollectionModel(allGroups);
    }

    @GetMapping("/{groupId}")
    public GroupModel search(@PathVariable Long groupId) {
        Group group = groupService.findOrFail(groupId);
        return groupModelAssembler.toModel(group);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupModel add(@RequestBody @Valid GroupInput groupInput) {
        Group group = groupInputDisassembler.toDomainObject(groupInput);

        group = groupService.save(group);

        return groupModelAssembler.toModel(group);
    }

    @PutMapping("/{groupId}")
    public GroupModel update(@PathVariable Long groupId, @RequestBody @Valid GroupInput groupInput) {
        Group currentGroup = groupService.findOrFail(groupId);

        currentGroup = groupService.save(currentGroup);

        return groupModelAssembler.toModel(currentGroup);
    }

    @DeleteMapping("/{groupId}")
    public void remove(@PathVariable Long groupId) {
        groupService.remove(groupId);
    }

}
