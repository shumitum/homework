package org.example.user.service;

import org.example.user.model.User;

public interface AuthenticationService {
    void authenticateUser();

    String getAuthorizedUserName();

    User getAuthorizedUser();

    boolean checkAuthorizedUserExistence();
}
