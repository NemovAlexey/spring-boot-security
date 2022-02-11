package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userRepository, RoleDao roleRepository) {
        this.userDao = userRepository;
        this.roleDao = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public void save(User user, String[] roleNames) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            roles.add(roleDao.findRoleByName(roleName));
        }
        user.setRoles(roles);
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
