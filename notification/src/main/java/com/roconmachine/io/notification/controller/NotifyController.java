package com.roconmachine.io.notification.controller;



import com.roconmachine.io.interfaces.NotifyApi;

import com.roconmachine.io.models.RequestNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("${api.version}${api.service}")
public class NotifyController  implements NotifyApi {

    @PostMapping("/notify")
    public ResponseEntity<Void> addNotification(RequestNotification notification) {
        return new ResponseEntity<Void>( HttpStatus.CREATED );
    }
}
