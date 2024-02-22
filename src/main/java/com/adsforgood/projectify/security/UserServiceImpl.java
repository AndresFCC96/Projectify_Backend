package com.adsforgood.projectify.security;

import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Optional<User> user = userRepository.findOneByEmail(email);
                if (user.isEmpty()){
                    throw new UsernameNotFoundException("User with email " + email + " couldn't been found");
                }else {
                    return user.get();
                }
            }
        };
    }
}
