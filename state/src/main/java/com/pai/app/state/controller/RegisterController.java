package com.pai.app.state.controller;

import com.pai.app.state.converter.StateConverter;
import com.pai.app.state.entities.DomainObjectStateEntity;
import com.pai.app.state.entities.StateEntity;
import com.pai.app.state.services.DomainObjectStateService;
import com.pai.app.state.services.StateService;
import com.roconmachine.io.dataframe.state.interfaces.RegisterApi;
import com.roconmachine.io.dataframe.state.models.State;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class RegisterController implements RegisterApi {

    private final DomainObjectStateService domainObjectStateService;
    private final StateService stateService;
    private final StateConverter stateConverter;
    @Override
    public ResponseEntity<State> registerMe(Long objetId, String domainClass) {
        StateEntity stateEntity = stateService.findRoot("PAI");
        if (stateEntity == null) return ResponseEntity.notFound().build();

        DomainObjectStateEntity domainObjectStateEntity = this.domainObjectStateService.save(
                DomainObjectStateEntity.builder()
                        .className(domainClass)
                        .objectId(objetId)
                        .stateId(stateEntity.getId())
                        .build()
        );
        if (domainObjectStateEntity != null)
            return ResponseEntity.ok(stateConverter.toModel(stateEntity));
        else return ResponseEntity.notFound().build();
    }
}
