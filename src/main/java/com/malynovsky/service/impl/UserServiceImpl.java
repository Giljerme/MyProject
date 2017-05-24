package com.malynovsky.service.impl;

import com.malynovsky.dao.RoleDao;
import com.malynovsky.dao.UserDao;
import com.malynovsky.dto.UserDto;
import com.malynovsky.entity.Rate;
import com.malynovsky.entity.Role;
import com.malynovsky.entity.User;
import com.malynovsky.service.RateService;
import com.malynovsky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ivan on 08.04.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RateService rateService;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<UserDto> getAll() {
        List<Rate> rates = rateService.getAllUniqueRates();
        List<UserDto> dtos = userDao.findAll().stream().map(UserDto::new).collect(Collectors.toList());

        dtos.forEach(p -> p.setVoices(rates.stream()
                .filter(rate -> Objects.equals(rate.getIdUser(), p.getId())).count()));
        dtos.forEach(p -> p.setRates(rates.stream()
                .filter(rate -> Objects.equals(rate.getIdUser(), p.getId()))
                .collect(Collectors.averagingInt(Rate::getRateValue))));

        return dtos;
    }

    @Override
    public List<UserDto> getTopVoters() {

        return getAll().stream().sorted((UserDto d1, UserDto d2) -> Long.compare(d2.getVoices(), d1.getVoices()))
                .limit(10).collect(Collectors.toList());
    }

    @Override
    public long getAllUsersCount() {
        return userDao.count();
    }

    @Override
    public List<UserDto> getAllSortedUsers(String columnName, int direction) {
        List<UserDto> dtos = getAll();
        Comparator<UserDto> function;

        switch (columnName) {
            case "User":
                function = (UserDto d1, UserDto d2) -> d1.getUsername().compareTo(d2.getUsername()) * direction;
                break;
            case "Vote":
                function = (UserDto d1, UserDto d2) -> Long.compare(d2.getVoices(), d1.getVoices()) * direction;
                break;
            default:
                function = (UserDto d1, UserDto d2) -> Double.compare(d2.getRates(), d1.getRates()) * direction;
                break;
        }

        return dtos.stream().sorted(function).collect(Collectors.toList());
    }
}
