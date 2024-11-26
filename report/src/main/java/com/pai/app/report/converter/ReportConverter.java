package com.pai.app.report.converter;

import com.pai.app.report.entity.LocationEntity;
import com.pai.app.report.entity.MediaContentEntity;
import com.pai.app.report.entity.ReportEntity;
import com.roconmachine.io.dataframe.report.models.Location;
import com.roconmachine.io.dataframe.report.models.MediaContent;
import com.roconmachine.io.dataframe.report.models.Report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ReportConverter {

    public static ReportEntity toEntity(Report report){
        ReportEntity entity = new ReportEntity();
        entity.setTitle(report.getTitle());
        entity.setDescription(report.getDescription());
        entity.setPriority(Objects.requireNonNull(report.getPriority()).getValue());
        entity.setTags(String.join(",", Objects.requireNonNull(report.getTags())));
        entity.setReporter(report.getReporter());
        List<MediaContentEntity> mediaContentEntities = new ArrayList<>();
        for(MediaContent mc : Objects.requireNonNull(report.getMedia())){
            MediaContentEntity entity1 = new MediaContentEntity();
            entity1.setMediaType(mc.getType());
            entity1.setMediaSource(mc.getName());
            entity1.setReport(entity);
            mediaContentEntities.add(entity1);
        }
        entity.setMediaContents(mediaContentEntities);

        List<LocationEntity> locationList = new ArrayList<>(0);
        for(Location location : Objects.requireNonNull(report.getLocation())){
            LocationEntity entityLocation = new LocationEntity();
            entityLocation.setName(location.getName());
            entityLocation.setAddress(location.getAddress());
            entityLocation.setLat(location.getLat());
            entityLocation.setLng(location.getLng());
            entityLocation.setUrl(location.getGoogleLocationUrl());
            entityLocation.setReport(entity);
            locationList.add(entityLocation);
        }
        entity.setLocations(locationList);
        return entity;
    }

    public static Report toModel(ReportEntity entity){
        Report report = new Report();

        report.setId(entity.getId());
        report.setTitle(entity.getTitle());
        report.setDescription(entity.getDescription());
        report.setPriority(Report.PriorityEnum.fromValue(entity.getPriority()));
        report.setTags(Arrays.asList(entity.getTags().split(","))); // Split tags back into a list
        report.setReporter(entity.getReporter());

        // Map MediaContent
        List<MediaContent> mediaContents = new ArrayList<>();
        for (MediaContentEntity mcEntity : Objects.requireNonNull(entity.getMediaContents())) {
            MediaContent mediaContent = new MediaContent();
            mediaContent.setType(mcEntity.getMediaType());
            mediaContent.setName(mcEntity.getMediaSource());

            mediaContents.add(mediaContent);
        }
        report.setMedia(mediaContents);


        List<Location> locations = new ArrayList<>();
        for (LocationEntity locationEntity : Objects.requireNonNull(entity.getLocations())) {
            Location location = new Location();
            location.setAddress(locationEntity.getAddress());
            location.setLat(locationEntity.getLat());
            location.setLng(locationEntity.getLng());
            location.setGoogleLocationUrl(locationEntity.getUrl());
            location.setName(locationEntity.getName());
            locations.add(location);
        }

        report.setLocation(locations);

        return report;
    }
}
