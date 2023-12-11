package com.crio.jukebox.repositories;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = new UserRepository();
    }

    @Test
    public void testSave() {
        // Create a new user
        User user = new User("JohnDoe");

        // Save the user
        User savedUser = userRepository.save(user);

        // Check that the user was saved with a non-null ID
        assertNotNull(savedUser.getId());

        // Check that the saved user matches the original user
        assertEquals(user.getUserName(), savedUser.getUserName());
        assertEquals(user.getPlayList(), savedUser.getPlayList());
        assertEquals(1, savedUser.getId());
    }

    @Test
    public void testFindAll() {
        // Create a few users
        User user1 = new User("User1");
        User user2 = new User("User2");

        // Save the users
        userRepository.save(user1);
        userRepository.save(user2);

        // Retrieve all users
        List<User> users = userRepository.findAll();

        // Check that the number of retrieved users matches the number of saved users
        assertEquals(2, users.size());
    }

    @Test
    public void testFindById() {
        // Create a new user
        User user = new User("JohnDoe");

        // Save the user
        User savedUser = userRepository.save(user);

        // Retrieve the user by ID
        Optional<User> retrievedUser = userRepository.findById(savedUser.getId());

        // Check that the retrieved user is present and matches the saved user
        assertTrue(retrievedUser.isPresent());
        assertEquals(savedUser, retrievedUser.get());
    }

    @Test
    public void testExistsById() {
        // Create a new user
        User user = new User("JohnDoe");

        // Save the user
        User savedUser = userRepository.save(user);

        // Check if the user exists by ID
        assertTrue(userRepository.existsById(savedUser.getId()));
    }

    @Test
    public void testCount() {
        // Create a few users
        User user1 = new User("User1");
        User user2 = new User("User2");

        // Save the users
        userRepository.save(user1);
        userRepository.save(user2);

        // Check the count of users
        assertEquals(2, userRepository.count());
    }

    @Test
    public void testDelete() {
        // Create a new user
        User user = new User("JohnDoe");

        // Save the user
        User savedUser = userRepository.save(user);

        // Delete the user
        userRepository.delete(savedUser);

        // Check that the user no longer exists
        assertFalse(userRepository.existsById(savedUser.getId()));
    }

    @Test
    public void testDeleteById() {
        // Create a new user
        User user = new User("JohnDoe");

        // Save the user
        User savedUser = userRepository.save(user);

        // Delete the user by ID
        userRepository.deleteById(savedUser.getId());

        // Check that the user no longer exists
        assertFalse(userRepository.existsById(savedUser.getId()));
    }

    @Test
    public void testUserHasPlayList() {
        // Create a user with a playlist
        User user = new User("JohnDoe");
        user.addPlayList(Arrays.asList(1, 2, 3));

        // Save the user
        User savedUser = userRepository.save(user);

        // Check if the user has a specific playlist
        Optional<User> userWithPlaylist = userRepository.userHasPlayList(savedUser.getId(), 2);

        // Check that the user with the specified playlist is present
        assertTrue(userWithPlaylist.isPresent());
    }

    @Test
    public void testAddPlayList() {
        // Create a user
        User user = new User("JohnDoe");

        // Save the user
        User savedUser = userRepository.save(user);

        // Add a playlist to the user
        User userWithPlaylist = userRepository.addPlayList(savedUser.getId(), 5);

        // Check that the user now has the added playlist
        assertTrue(userWithPlaylist.getPlayList().contains(5));
    }

    @Test
    public void testRemovePlayList() {
        // Create a user with a playlist
        User user = new User("JohnDoe");
        user.addPlayList(Arrays.asList(1, 2, 3));

        // Save the user
        User savedUser = userRepository.save(user);

        // Remove a playlist from the user
        userRepository.removePlayList(savedUser.getId(), 2);

        // Check that the user no longer has the removed playlist
        assertFalse(savedUser.getPlayList().contains(2));
    }
}
