package com.roconmachine.io.fgaccess.converters;


import com.roconmachine.io.dataframe.fgaccess.models.Subject;
import com.roconmachine.io.fgaccess.entity.SubjectEntity;

public class SubjectConverter {

    public static Subject toModel(SubjectEntity entity){
        return Subject.builder()
                .id(entity.subject_id)
                .subjectId(entity.user_id)
                .status(entity.status)
                .build();
    }

    public static SubjectEntity toEntity(Subject model){
        SubjectEntity entity = new SubjectEntity();
        if (model.getSubjectId() != null)
            entity.setUser_id(model.getSubjectId());
        if (model.getStatus() != null)
            entity.setStatus(model.getStatus());
        return entity;
    }
}
