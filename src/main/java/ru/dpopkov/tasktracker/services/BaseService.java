package ru.dpopkov.tasktracker.services;

import ru.dpopkov.tasktracker.model.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity> {

    boolean create(T entity);

    Optional<T> findById(Long id);

    List<T> getAll();

    boolean delete(Long id);
}
