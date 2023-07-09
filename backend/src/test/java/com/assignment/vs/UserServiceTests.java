package com.assignment.vs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.assignment.vs.domain.UserInfo;
import com.assignment.vs.repository.UserRepository;
import com.assignment.vs.service.UserService;

@SpringBootTest
public class UserServiceTests {
    
    @Mock
    UserRepository userRepository;
    
    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testSaveOrGet_NewUser() {
        UserInfo newUser = new UserInfo("John");
        when(userRepository.findByName(newUser.getName())).thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(newUser);
        UserInfo savedUser = userService.saveOrGet(newUser);
        verify(userRepository).save(newUser);
        assertEquals(newUser, savedUser);
    }

    @Test
    public void testSaveOrGet_ExistingUser() {
        UserInfo existingUser = new UserInfo("Jane");
        when(userRepository.findByName(existingUser.getName())).thenReturn(Optional.of(existingUser));
        UserInfo retrievedUser = userService.saveOrGet(existingUser);
        verify(userRepository, Mockito.never()).save(existingUser);
        assertEquals(existingUser, retrievedUser);
    }

    @Test
    public void testGetUser_UserNotFound() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        UserInfo user = userService.getUser(1L);
        verify(userRepository).findById(1L);
        assertNull(user);
    }

    @Test
    public void testGetUser_UserFound() {
        UserInfo user = new UserInfo("Alice");
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserInfo retrievedUser = userService.getUser(1L);
        verify(userRepository).findById(1L);
        assertEquals(user, retrievedUser);
    }
}
