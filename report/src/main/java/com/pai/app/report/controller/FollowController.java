package com.pai.app.report.controller;

import com.pai.app.report.entity.FollowEntity;
import com.pai.app.report.service.FollowService;
import com.roconmachine.io.dataframe.report.interfaces.FollowApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class FollowController implements FollowApi {
    private final FollowService followService;
    @Override
    public ResponseEntity<Void> follow(Long report_id, String user_id) {

        Optional<FollowEntity> savedFollow = followService.save(FollowEntity.builder()
                .userId(user_id)
                .report(report_id)
                .build());
        if (savedFollow.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}
