package com.ora.movieapi.services;
        import com.ora.movieapi.config.UserPrincipal;
        import com.ora.movieapi.entities.User;
        import com.ora.movieapi.repositories.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import  org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName);
        if(user == null)
            throw new UsernameNotFoundException("No Access");
        return new UserPrincipal(user);
    }
}
