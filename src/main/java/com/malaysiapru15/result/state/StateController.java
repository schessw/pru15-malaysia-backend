package com.malaysiapru15.result.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class StateController {
    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping(path = "/state")
    public List<State> getState(@RequestParam(required = false, name = "name") String state_name) {
        if(state_name == null) {
            return stateService.getState();
        } else {
            return stateService.getState(state_name);
        }
    }

    @GetMapping(path = "/state/{id}")
    public Optional<State> getStateById(@PathVariable("id") long state_id) {
        return stateService.getStateById(state_id);
    }

    @PostMapping(path = "/state")
    public void addState(@RequestBody State state) {
        stateService.addState(state);
    }

    @PutMapping(path = "/state/{id}")
    public void updateState(@RequestBody State state,
                            @PathVariable("id") Long state_id) {
        stateService.updateState(state, state_id);
    }

    @DeleteMapping(path = "/state/{id}")
    public void deleteState(@PathVariable("id") Long state_id) {
        stateService.deleteState(state_id);
    }
}
