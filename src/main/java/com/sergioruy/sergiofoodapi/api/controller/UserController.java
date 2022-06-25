package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.UserInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.UserModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.UserModel;
import com.sergioruy.sergiofoodapi.api.model.input.PasswordInput;
import com.sergioruy.sergiofoodapi.api.model.input.UserInput;
import com.sergioruy.sergiofoodapi.api.model.input.UserWithPasswordInput;
import com.sergioruy.sergiofoodapi.domain.model.User;
import com.sergioruy.sergiofoodapi.domain.repository.UserRepository;
import com.sergioruy.sergiofoodapi.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @Autowired
    private UserInputDisassembler userInputDisassembler;

    @GetMapping
    public List<UserModel> list() {
        List<User> allUsers = userRepository.findAll();

        return userModelAssembler.toCollectionModel(allUsers);
    }

    @GetMapping("/{userId}")
    public UserModel find(@PathVariable Long userId) {
        User user = userService.findOrFail(userId);

        return userModelAssembler.toModel(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel add(@RequestBody @Valid UserWithPasswordInput userInput) {
        User user = userInputDisassembler.toDomainObject(userInput);
        user = userService.save(user);

        return userModelAssembler.toModel(user);
    }

    @PutMapping("/{userId}")
    public UserModel update(@PathVariable Long userId, @RequestBody @Valid UserInput userInput) {
        User currentUser = userService.findOrFail(userId);
        userInputDisassembler.copyToDomainObject(userInput, currentUser);
        currentUser = userService.save(currentUser);

        return userModelAssembler.toModel(currentUser);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long userId, @RequestBody @Valid PasswordInput password) {
        userService.changePassword(userId, password.getCurrentPassword(), password.getNewPassword());
    }
}
