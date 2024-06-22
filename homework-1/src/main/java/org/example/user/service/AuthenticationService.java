package org.example.user.service;

import org.example.user.model.User;

public interface AuthenticationService {
    public void authenticateUser();
    String getAuthorizedUserName();
    User getAuthorizedUser();
    boolean checkAuthorizedUserExistence();
}
