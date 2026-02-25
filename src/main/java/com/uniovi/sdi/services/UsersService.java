package com.uniovi.sdi.services;

import java.util.*;

import com.uniovi.sdi.entities.Mark;
import com.uniovi.sdi.entities.User;
import com.uniovi.sdi.repositories.UsersRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UsersService(UsersRepository usersRepository,  BCryptPasswordEncoder
            bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostConstruct
    public void init() {
    }
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }
    public User getUser(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public User getUserByDni(String dni) {
        return usersRepository.findByDni(dni);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    public void updateUser(User user) {
        User oldUser = usersRepository.findById(user.getId()).get();
        oldUser.setDni(user.getDni());
        oldUser.setName(user.getName());
        oldUser.setLastName(user.getLastName());
        usersRepository.save(oldUser);
    }

    public Page<User> searchUsersByNameAndSurname(Pageable pageable, String searchText) {
        Page<User> users = new PageImpl<User>(new LinkedList<User>());
        searchText = "%" + searchText + "%";

        users = usersRepository.searchByNameAndSurname(pageable, searchText);
        return users;
    }

    public Page<User> findAllUsers(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }
}