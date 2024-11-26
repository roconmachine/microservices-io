package com.pai.app.report.service;

import com.pai.app.report.core.BaseService;
import com.pai.app.report.entity.LocationEntity;
import com.pai.app.report.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService extends BaseService<LocationEntity, LocationRepository> {
}
