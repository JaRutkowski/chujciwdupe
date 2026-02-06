package com.jfs.operations.lite.dto.account.impl;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.model.account.domain.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl { //} implements CustomUserDetailsService {
    private final RestClient restClient; //TODO: #12 to be refactored

//    @Override
//    public UserDetails loadUserByUsername(String login) {
//        UserData userData = findByLogin(login);
//        return userData;
//    }
//
//    @Override
//    public void createUser(String login, String password) throws NoSuchAlgorithmException, LoginException {
//        //TODO: Handle salt
//        if (findByLogin(login) != null)
//            throw new LoginException("User with given login already exists");
//        restClient.postSync("/api/user?login=" + login + "&password=" + password, String.class);
//    }

    private UserData findByLogin(String login) {
        return (UserData) restClient.getSync("/api/user?login=" + login, UserData.class);
    }
}
