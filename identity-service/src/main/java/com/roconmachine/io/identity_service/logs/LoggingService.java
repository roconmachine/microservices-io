package com.roconmachine.io.identity_service.logs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoggingService {

    void displayReq(HttpServletRequest request, Object body);

    void displayResp(HttpServletRequest request, HttpServletResponse response, Object body);
}
