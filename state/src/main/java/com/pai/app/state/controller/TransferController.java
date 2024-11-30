package com.pai.app.state.controller;

import com.pai.app.state.converter.LifecycleConverter;
import com.pai.app.state.converter.StateConverter;
import com.pai.app.state.entities.LifecycleEntity;
import com.pai.app.state.entities.StateEntity;
import com.pai.app.state.services.LifecycleService;
import com.pai.app.state.services.StateService;
import com.roconmachine.io.dataframe.state.interfaces.TransferApi;
import com.roconmachine.io.dataframe.state.models.State;
import com.roconmachine.io.dataframe.state.models.StateMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class TransferController implements TransferApi {

    private final LifecycleService lifecycleService;
    private final LifecycleConverter lifecycleConverter;
    private final StateService stateService;
    private final StateConverter stateConverter;
    @Override
    public ResponseEntity<State> transferStateidActionidGet(Long stateid, Long actionid) {

        List<StateMapping> list = lifecycleService.searchNext(stateid, actionid).stream()
                .map(lifecycleConverter::toModel)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            return ResponseEntity.notFound().build(); // HTTP 204 No Content
        }
        Optional<StateEntity> entity= stateService.getById(list.get(0).getToState());
        if (entity.isPresent())
        {
            return ResponseEntity.status(HttpStatus.OK).body(stateConverter.toModel(entity.get())); // HTTP 200 OK with body

        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("test2")
    public ResponseEntity<Void> getActionsByCurrentState(@RequestParam("state") Long stateid){
        List<LifecycleEntity> list =  lifecycleService.findAllByFromState(2L);
        if (!list.isEmpty())
        {
            int size = list.size();
        }
        return null;
    }
}
