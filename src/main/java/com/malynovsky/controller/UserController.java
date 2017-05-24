package com.malynovsky.controller;

import com.malynovsky.dto.UserDto;
import com.malynovsky.entity.User;
import com.malynovsky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ivan on 09.04.2017.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/all", method = GET)
    public @ResponseBody List<UserDto> getAllUsers(@RequestParam Long page,
                                                   @RequestParam String columnForSorting,
                                                   @RequestParam Integer direction) {

        return userService.getAllSortedUsers(columnForSorting, direction != 0 ? direction : -1)
                .stream().skip(page * 5).limit(5).collect(Collectors.toList());
    }

    @RequestMapping(value = "/top-users", method = GET)
    public @ResponseBody List<UserDto> getTopUsers() {

        return userService.getTopVoters();
    }

    @RequestMapping(value = "/user-all", method = GET)
    public String userAllPage() {
        return "user-all";
    }

    @RequestMapping(value = "/user-count", method = GET)
    public @ResponseBody Long getUserCount() {
        return userService.getAllUsersCount();
    }
}
