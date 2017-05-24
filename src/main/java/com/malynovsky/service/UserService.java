package com.malynovsky.service;

import com.malynovsky.dto.UserDto;
import com.malynovsky.entity.User;

import java.util.List;

/**
 * Created by Ivan on 08.04.2017.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);

    List<UserDto> getAll();

    List<UserDto> getTopVoters();

    long getAllUsersCount();

    List<UserDto> getAllSortedUsers(String columnName, int direction);
}
