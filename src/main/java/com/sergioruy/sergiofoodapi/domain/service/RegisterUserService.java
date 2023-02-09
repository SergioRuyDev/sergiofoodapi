package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import com.sergioruy.sergiofoodapi.domain.exception.UserNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Group;
import com.sergioruy.sergiofoodapi.domain.model.User;
import com.sergioruy.sergiofoodapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterGroupService groupService;

    @Transactional
    public User save(User user) {
        userRepository.detach(user);

        Optional<User> userExisting = userRepository.findByEmail(user.getEmail());

        if (userExisting.isPresent() && !userExisting.get().equals(user)) {
            throw new BusinessException(
                    String.format("User with the email %s already exist", user.getEmail()));
        }
        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = findOrFail(userId);

        if (user.passwordNotEquals(currentPassword)) {
            throw new BusinessException("Password informed not match with the current user.");
        }

        user.setPassword(newPassword);
    }

    @Transactional
    public void detachGroup(Long userId, Long groupId) {
        User user = findOrFail(userId);
        Group group = groupService.findOrFail(groupId);

        user.getGroups().remove(group);
    }

    @Transactional
    public void attachGroup(Long userId, Long groupId) {
        User user = findOrFail(userId);
        Group group = groupService.findOrFail(groupId);

        user.getGroups().add(group);
    }

    public User findOrFail(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
