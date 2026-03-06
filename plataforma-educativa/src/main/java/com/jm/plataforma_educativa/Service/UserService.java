package com.jm.plataforma_educativa.Service;

import com.jm.plataforma_educativa.Entity.UserSec;
import com.jm.plataforma_educativa.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public List<UserSec> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserSec> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserSec save(UserSec user) {
        return userRepository.save(user);
    }

    public UserSec update(UserSec user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            UserSec newUser = new UserSec();
            newUser.setId(user.getId());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            newUser.setEnable(user.isEnable());
            newUser.setAccountNonLocked(user.isAccountNonLocked());
            newUser.setCredentialsNonExpired(user.isCredentialsNonExpired());
            newUser.setAccountNotExpired(user.isAccountNotExpired());
            newUser.setRolList(user.getRolList());
            userRepository.save(newUser);
        }
        return null;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
