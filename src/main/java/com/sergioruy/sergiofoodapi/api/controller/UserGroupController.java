package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.GroupModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.GroupModel;
import com.sergioruy.sergiofoodapi.domain.model.User;
import com.sergioruy.sergiofoodapi.domain.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users/{userId}/groups")
public class UserGroupController {

    @Autowired
    private RegisterUserService userService;

    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @GetMapping
    public List<GroupModel> list(@PathVariable Long userId) {
        User user = userService.findOrFail(userId);

        return groupModelAssembler.toCollectionModel(user.getGroups());
    }


    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void attach(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.attachGroup(userId, groupId);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void detach(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.detachGroup(userId, groupId);
    }
}
