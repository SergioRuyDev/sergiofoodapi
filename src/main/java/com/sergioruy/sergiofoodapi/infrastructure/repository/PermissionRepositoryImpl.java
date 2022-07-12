package com.sergioruy.sergiofoodapi.infrastructure.repository;

import com.sergioruy.sergiofoodapi.domain.model.Permission;
import com.sergioruy.sergiofoodapi.domain.repository.PermissionRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permission> list() {
        return manager.createQuery("from Permission", Permission.class).getResultList();
    }

    @Override
    public Permission find(Long id) {
        return manager.find(Permission.class, id);
    }

    @Transactional
    @Override
    public Permission save(Permission permission) {
        return manager.merge(permission);
    }

    @Transactional
    @Override
    public void Remove(Permission permission) {
        permission = find(permission.getId());
        manager.remove(permission);
    }

    @Override
    public List<Permission> findAll() {
        return null;
    }

    @Override
    public List<Permission> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Permission> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Permission> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Permission permission) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Permission> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Permission> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Permission> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Permission> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Permission> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Permission> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Permission getOne(Long aLong) {
        return null;
    }

    @Override
    public Permission getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Permission> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Permission> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Permission> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Permission> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Permission> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Permission> boolean exists(Example<S> example) {
        return false;
    }
}
