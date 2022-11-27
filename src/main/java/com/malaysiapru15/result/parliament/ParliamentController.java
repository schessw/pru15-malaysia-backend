package com.malaysiapru15.result.parliament;

import com.malaysiapru15.result.state.State;
import com.malaysiapru15.result.state.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1")
public class ParliamentController {
    @Autowired
    private final StateService stateService;

    @Autowired
    private final ParliamentService parliamentService;

    @Autowired
    public ParliamentController(ParliamentService parliamentService, StateService stateService) {
        this.parliamentService = parliamentService;
        this.stateService = stateService;
    }

    @GetMapping(path = "/parliament")
    public ResponseEntity<List> getParliament(@RequestParam(required=false, name="name") String parliament_name,
                                          @RequestParam(required=false, name="code") String parliament_code) {
        try {
            List<Parliament> parliaments;

            if(parliament_name != null) {
                parliaments = parliamentService.getParliamentByName(parliament_name);
            } else if(parliament_code != null) {
                parliaments = parliamentService.getParliamentByCode(parliament_code);
            } else {
                parliaments = parliamentService.getParliament();
            }

            return new ResponseEntity<List>(parliaments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/parliament/{id}")
    public ResponseEntity<Parliament> getParliamentById(@PathVariable("id") long parliament_id) {
        try {
            Optional<Parliament> parliament = parliamentService.getParliamentById(parliament_id);
            if (parliament.isPresent()) {
                return new ResponseEntity<>(parliament.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/state/{id}/parliament")
    public ResponseEntity<List<Parliament>> getParliamentByState(@PathVariable(value = "id") long state_id) {
        try {
            Optional<State> states = stateService.getStateById(state_id);

            if(states.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                List<Parliament> parliaments = parliamentService.getParliamentByState(state_id);
                return new ResponseEntity<>(parliaments, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/state/{id}/parliament")
    public ResponseEntity<Map<String, Object>> addParliament(@PathVariable(value = "id") long state_id,
                                                    @RequestBody Parliament parliament) {
        try {
            Optional<State> state = stateService.getStateById(state_id);

            if (state.isPresent()) {
                parliament.setState(state.get());
            }

            Map<String, Object> response = new HashMap<>();

            if (parliament.getName() != null && parliament.getCode() != null) {
                Parliament p = parliamentService.addParliament(parliament);

                response.put("message", "Data has been inserted.");
                response.put("data", p);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                response.put("message", "Invalid request body: Missing 'name' or 'code'. ");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/parliament/{id}")
    public ResponseEntity<Map<String, Object>> updateParliament(@RequestBody Parliament parliament,
                                                                @PathVariable("id") long parliament_id) {
        Parliament p = parliamentService.updateParliament(parliament, parliament_id);

        Map<String, Object> response = new HashMap<>();

        if(p.getName() != null && p.getCode() != null) {
            response.put("message", "Data has been updated.");
            response.put("data", p);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Parliament with ID: " + parliament_id + " cannot be found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/parliament/{id}")
    public ResponseEntity<HttpStatus> deleteParliamentById(@PathVariable("id") long parliament_id) {
        try {
            parliamentService.deleteParliament(parliament_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
