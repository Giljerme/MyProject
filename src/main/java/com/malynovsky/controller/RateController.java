package com.malynovsky.controller;

import com.malynovsky.dto.RateDto;
import com.malynovsky.service.RateService;
import com.malynovsky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Ivan on 09.04.2017.
 */
@Controller
@RequestMapping(value = "rate/")
public class RateController {

    @Autowired
    private RateService rateService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "add", method = POST, headers="Accept=*/*")
    public @ResponseBody String addRate(@RequestParam String userName, @RequestParam Long idMovie,
                   @RequestParam Integer rate) {

        rateService.addRate(new RateDto(idMovie, userService.findByUsername(userName).getId(), rate));

        return "ok";
    }
}
