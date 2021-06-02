package ru.dpopkov.tasktracker.services;

import ru.dpopkov.tasktracker.model.User;
import ru.dpopkov.tasktracker.repositories.UserRepository;

public class UserServiceImpl extends AbstractBaseService<User> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}
