package com.malaysiapru15.result.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api/v1")
public class PartyController {
    private final PartyService partyService;

    @Autowired
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping(path = "/party")
    public ResponseEntity<List> getParty(@RequestParam(required = false, name = "name") String party_name) {
        try {
            List<Party> parties;
            if(party_name == null) {
                parties = partyService.getParty();
            } else {
                parties = partyService.getParty(party_name);
            }
            return new ResponseEntity<List>(parties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/party/{id}")
    public ResponseEntity<Party> getPartyById(@PathVariable("id") long party_id) {
        try {
            Optional<Party> party = partyService.getPartyById(party_id);
            if (party.isPresent()) {
                return new ResponseEntity<>(party.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/party")
    public ResponseEntity<Map<String, Object>> addParty(@RequestBody Party party) {
        try {
            Map<String, Object> response = new HashMap<>();
            if (party.getName() != null) {
                Party p = partyService.addParty(party);

                response.put("message", "Data has been inserted.");
                response.put("data", p);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                response.put("message", "Invalid request body: Missing 'name'. ");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/party/{id}")
    public ResponseEntity<Map<String, Object>> updateParty(@RequestBody Party party,
                            @PathVariable("id") long party_id) {
        Party p = partyService.updateParty(party, party_id);
        Map<String, Object> response = new HashMap<>();

        if(p.getName() != null) {
            response.put("message", "Data has been updated.");
            response.put("data", p);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Party with ID: " + party_id + " cannot be found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/party/{id}")
    public ResponseEntity<HttpStatus> deleteParty(@PathVariable("id") long party_id) {
        try {
            partyService.deleteParty(party_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
