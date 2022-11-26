package com.malaysiapru15.result.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class PartyController {
    private final PartyService partyService;

    @Autowired
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping(path = "/party")
    public List<Party> getParty(@RequestParam(required = false, name = "name") String party_name) {
        if(party_name == null) {
            return partyService.getParty();
        } else {
            return partyService.getParty(party_name);
        }
    }

    @GetMapping(path = "/party/{id}")
    public Optional<Party> getPartyById(@PathVariable("id") long party_id) {
        return partyService.getPartyById(party_id);
    }

    @PostMapping(path = "/party")
    public void addParty(@RequestBody Party party) {
        partyService.addParty(party);
    }

    @PutMapping(path = "/party/{id}")
    public void updateParty(@RequestBody Party party,
                            @PathVariable("id") Long party_id) {
        partyService.updateParty(party, party_id);
    }

    @DeleteMapping(path = "/party/{id}")
    public void deleteParty(@PathVariable("id") Long party_id) {
        partyService.deleteParty(party_id);
    }
}
