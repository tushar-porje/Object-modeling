package com.crio.jukebox.services;

import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.entities.User;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    // Constructor for UserService that takes an IUserRepository as a parameter.
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String name) {
        // Create a new User object with the provided name.
        User user = new User(name);

        // Save the newly created user using the IUserRepository instance.
        // This method is responsible for persisting the user data in the data store.
        return userRepository.save(user);
    }
}
