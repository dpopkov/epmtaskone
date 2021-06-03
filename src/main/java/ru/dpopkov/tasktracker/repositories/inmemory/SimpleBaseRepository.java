package ru.dpopkov.tasktracker.repositories.inmemory;

import ru.dpopkov.tasktracker.model.BaseEntity;
import ru.dpopkov.tasktracker.repositories.BaseRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class SimpleBaseRepository<T extends BaseEntity> implements BaseRepository<T> {

    private long lastId = 0L;

    private final Map<Long, T> map = new ConcurrentHashMap<>();

    private synchronized Long nextId() {
        return ++lastId;
    }

    @Override
    public void save(T entity) {
        Long id = nextId();
        map.put(id, entity);
        entity.setId(id);
    }

    @Override
    public Optional<T> findById(Long id) {
        T found = map.get(id);
        return Optional.ofNullable(found);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public boolean deleteById(Long id) {
        T removed = map.remove(id);
        return removed != null;
    }
}
