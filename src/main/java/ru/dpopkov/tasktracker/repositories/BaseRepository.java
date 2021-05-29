package ru.dpopkov.tasktracker.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    void save(T entity);

    Optional<T> findById(Long id);

    List<T> getAll();

    boolean deleteById(Long id);
}
