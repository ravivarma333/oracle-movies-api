package com.ora.movieapi.services;

import com.ora.movieapi.entities.User;
import com.ora.movieapi.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userServiceUnderTest;

    @Before
    public void setUp(){
        userServiceUnderTest = new UserService();
        userServiceUnderTest.userRepository = mock(UserRepository.class);
    }


    @Test
    public void testLoadUserByUsername() {

        User user = User.builder().name("user").password("password").id(1L).build();
        // Setup
        when(userServiceUnderTest.userRepository.findByName("user")).thenReturn(user);
        // Run the test
        UserDetails check = userServiceUnderTest.loadUserByUsername("user");

        // Verify the results
        assertThat(check).isNotNull();
    }

}
