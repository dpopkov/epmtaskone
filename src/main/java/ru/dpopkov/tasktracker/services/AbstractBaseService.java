package ru.dpopkov.tasktracker.services;

import ru.dpopkov.tasktracker.model.BaseEntity;
import ru.dpopkov.tasktracker.repositories.BaseRepository;

import java.util.List;
import java.util.Optional;

public class AbstractBaseService<T extends BaseEntity> implements BaseService<T> {

    private final BaseRepository<T> repository;

    public AbstractBaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(T entity) {
        repository.save(entity);
        return true;
    }

    @Override
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
}
