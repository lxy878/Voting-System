package com.assignment.vs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    
    @Test
    void saveTest(){
        // check length
        // character check
        // unique
        UserInfo u = new UserInfo(1L, "abc");
        UserInfo u2 = new UserInfo(2L, "abc");
        when(userRepository.save(any(UserInfo.class))).thenReturn(u);
        UserInfo savedUser = userService.saveOrGet(u);
        assertEquals(u.getId(), savedUser.getId());
        savedUser = userService.saveOrGet(u2);
        assertEquals(u.getId(), savedUser.getId());
    }

}
