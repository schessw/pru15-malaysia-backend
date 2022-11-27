package com.malaysiapru15.result.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping(path = "/api/v1")
public class StateController {
    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping(path = "/state")
    public ResponseEntity<List> getState(@RequestParam(required = false, name = "name") String state_name) {
        try {
            List<State> states;
            if(state_name == null) {
                states = stateService.getState();
            } else {
                states = stateService.getState(state_name);
            }
            return new ResponseEntity<List>(states, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/state/{id}")
    public ResponseEntity<State> getStateById(@PathVariable("id") long state_id) {
        try {
            Optional<State> state = stateService.getStateById(state_id);
            if (state.isPresent()) {
                return new ResponseEntity<>(state.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/state/external")
    public ResponseEntity<List> getNews() {
        String uri = "https://jianliew.me/malaysia-api/state/v1/all.json";
        RestTemplate restTemplate = new RestTemplate();
        Object[] result = restTemplate.getForObject(uri, Object[].class);
        return new ResponseEntity<>(Arrays.asList(result), HttpStatus.OK);
    }

    @PostMapping(path = "/state")
    public ResponseEntity<Map<String, Object>> addState(@RequestBody State state) {
        try {
            Map<String, Object> response = new HashMap<>();
            if (state.getName() != null) {
                State s = stateService.addState(state);

                response.put("message", "Data has been inserted.");
                response.put("data", s);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                response.put("message", "Invalid request body: Missing 'name'. ");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(path = "/state/{id}")
    public ResponseEntity<Map<String, Object>> updateState(@RequestBody State state,
                            @PathVariable("id") long state_id) {
        State s = stateService.updateState(state, state_id);
        Map<String, Object> response = new HashMap<>();

        if(s.getName() != null) {
            response.put("message", "Data has been updated.");
            response.put("data", s);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "State with ID: " + state_id + " cannot be found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/state/{id}")
    public ResponseEntity<HttpStatus> deleteState(@PathVariable("id") long state_id) {
        try {
            stateService.deleteState(state_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
