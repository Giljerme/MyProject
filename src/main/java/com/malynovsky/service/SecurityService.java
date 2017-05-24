package com.malynovsky.service;

/**
 * Created by Ivan on 08.04.2017.
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
