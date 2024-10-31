package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.dataframe.access.models.AuthzRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public void checkAuth(AuthzRequest ar) {

    }

    private boolean checkUserResource(){
        return false;
    }
}
