package com.sparta.user.domain.service;

import com.sparta.user.domain.model.CustomUserDetails;
import com.sparta.user.domain.model.User;
import com.sparta.user.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).
                orElseThrow(()-> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }
}
