// package com.crio.jukebox.services;


// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import com.crio.jukebox.entities.User;
// import com.crio.jukebox.repositories.IUserRepository;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;


// @ExtendWith(MockitoExtension.class)
// public class UserServiceTest {
    
//     private IUserRepository userRepository;
    
//     private UserService userService;

    

//     @Test
//     public void testCreateUser() {
//         List<Integer> playList = new ArrayList<>();
//         User expectedUser=new User(1, "tushar",playList);

//         // Mock the userRepository's save method to return the testUser
//         // when(userRepository.save(testUser)).thenReturn(testUser);

//         // Call the createUser method
//         User createdUser = userService.createUser("tushar");

//         // Assert that the createdUser is equal to the testUser
//         assertEquals(expectedUser, createdUser);
//     }
// }
