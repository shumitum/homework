package org.example.user.service;

public interface AuthenticationService {
    public void authenticateUser();
    String getAuthorizedUserName();
    boolean checkAuthorizedUserExistence();
}
