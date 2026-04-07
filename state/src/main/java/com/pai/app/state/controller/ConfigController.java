package com.pai.app.state.controller;

import com.pai.app.state.entities.ActionEntity;
import com.pai.app.state.services.ActionService;
import com.pai.app.state.services.LifecycleService;
import com.roconmachine.io.dataframe.state.interfaces.ConfigApi;
import com.roconmachine.io.dataframe.state.models.StateMapping;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class ConfigController implements ConfigApi{
    private final LifecycleService lifecycleService;
    private final ActionService actionService;
    @Override
    public ResponseEntity<List<String>> avaiableActions(Long currentState) {
        List<String> actionNames = lifecycleService.findAllByFromState(currentState)
                .stream()
                .map(lifecycleEntity -> actionService.getById(lifecycleEntity.getActionId())) // Get Optional<ActionEntity>
                .filter(Optional::isPresent) // Filter out empty Optionals
                .map(Optional::get) // Get the actual ActionEntity from the Optional
                .map(ActionEntity::getName) // Extract the name of the action
                .collect(Collectors.toList()); // Collect into a list

        // Return the collected list wrapped in a ResponseEntity
        return ResponseEntity.ok(actionNames);
    }

    @Override
    public ResponseEntity<Void> configure(@Valid List<@Valid StateMapping> list) {
        return null;
    }
}
