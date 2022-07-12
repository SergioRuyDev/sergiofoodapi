package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.exception.GroupNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.Group;
import com.sergioruy.sergiofoodapi.domain.model.Permission;
import com.sergioruy.sergiofoodapi.domain.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterGroupService {

    private static String MSG_GROUP_USED = "Group of code %d cannot be remove because is in use.";

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RegisterPermissionService permissionService;

    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public void remove(Long groupId) {
        try {
            groupRepository.deleteById(groupId);
            groupRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new GroupNotFoundException(groupId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityUsedException(
                    String.format(MSG_GROUP_USED, groupId));
        }
    }

    @Transactional
    public void detachPermission(Long groupId, Long permissionId) {
        Group group = findOrFail(groupId);
        Permission permission = permissionService.findOrFail(permissionId);

        group.getPermissions().remove(permission);
    }

    @Transactional
    public void attachPermission(Long groupId, Long permissionId) {
        Group group = findOrFail(groupId);
        Permission permission = permissionService.findOrFail(permissionId);

        group.getPermissions().add(permission);
    }

    public Group findOrFail(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
    }
}
