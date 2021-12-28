package com.quiz.ManagementSystem.User;

import com.quiz.ManagementSystem.exception.InvalidEmailException;
import com.quiz.ManagementSystem.exception.UnAuthorizedUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public User create(CreateUserCommand userCommand, Role role) throws InvalidEmailException {
        Optional<User> user = userRepository.findByEmail(userCommand.getEmail());
        if (user.isPresent()) {
            throw new InvalidEmailException();
        }
        User newUser = User.create(userCommand, role);
        return userRepository.save(newUser);
    }

    public void updateUserDetails(CreateUserCommand userCommand) {
        Optional<User> user = userRepository.findByEmail(userCommand.getEmail());
        if (user.isPresent()) {
            userRepository.save(user.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            return new UsernameNotFoundException("User not found");
        });

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    public boolean validateUserDetails(UserLoginDetails userLoginDetails) throws UnAuthorizedUserException {
        UserDetails userDetails = loadUserByUsername(userLoginDetails.getEmail());
        if (User.isPasswordValid(userDetails.getPassword(), userLoginDetails.getPassword())) {
            return true;
        } else {
            throw new UnAuthorizedUserException();
        }
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String userRole = user.getRole().toString();
        return AuthorityUtils.createAuthorityList(userRole);
    }
}
