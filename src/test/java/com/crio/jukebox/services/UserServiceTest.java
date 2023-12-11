package com.crio.jukebox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.*;
import com.crio.jukebox.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    private UserService userService;
    private IUserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(IUserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testCreateUser() {
        // Arrange
        User expectedUser=new User(1,"tushar",new ArrayList<>());
        // Mock the behavior of userRepository.save
        // when(userRepository.save(newUser)).thenReturn(expectedUser);
        
        // Act
        User actualUser = userService.createUser("tushar");
        
        // Assert
        assertEquals(expectedUser, actualUser);
        // Verify that userRepository.save was called with the new user
        // verify(userRepository, times(1)).save(newUser);
    }
}
