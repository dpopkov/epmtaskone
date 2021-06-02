package ru.dpopkov.tasktracker.repositories.inmemory;

import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.repositories.UserRepository;

public class SimpleUserRepository extends SimpleBaseRepository<User> implements UserRepository {
}
