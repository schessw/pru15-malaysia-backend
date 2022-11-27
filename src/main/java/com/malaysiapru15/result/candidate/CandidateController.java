package com.malaysiapru15.result.candidate;

import com.malaysiapru15.result.parliament.Parliament;
import com.malaysiapru15.result.parliament.ParliamentService;
import com.malaysiapru15.result.party.Party;
import com.malaysiapru15.result.party.PartyService;
import com.malaysiapru15.result.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path="/api/v1")
public class CandidateController {
    @Autowired
    private final CandidateService candidateService;

    @Autowired
    private final PartyService partyService;

    @Autowired
    private final ParliamentService parliamentService;

    @Autowired
    public CandidateController(CandidateService candidateService, PartyService partyService, ParliamentService parliamentService) {
        this.candidateService = candidateService;
        this.partyService = partyService;
        this.parliamentService = parliamentService;
    }

    @GetMapping(path = "/candidate")
    public ResponseEntity<List> getCandidate(@RequestParam(required=false, name="full_name") String candidate_full_name,
                                             @RequestParam(required=false, name="display_name") String candidate_display_name,
                                             @RequestParam(required=false, name="status") String candidate_status) {
        try {
            List<Candidate> candidates;

            if(candidate_full_name != null) {
                candidates = candidateService.getCandidateByFullName(candidate_full_name);
            } else if(candidate_display_name != null) {
                candidates = candidateService.getCandidateByDisplayName(candidate_display_name);
            } else if(candidate_status != null) {
                candidates = candidateService.getCandidateByStatus(candidate_status);
            } else {
                candidates = candidateService.getCandidate();
            }

            return new ResponseEntity<List>(candidates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/candidate/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") long candidate_id) {
        try {
            Optional<Candidate> candidate = candidateService.getCandidateById(candidate_id);
            if (candidate.isPresent()) {
                return new ResponseEntity<>(candidate.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/party/{id}/candidate")
    public ResponseEntity<Map<String, Object>> getCandidatePageByPartyId(
            @PathVariable("id") long id,
            @RequestParam(defaultValue = "0", name="page") int page,
            @RequestParam(defaultValue = "10", name="size") int size
    ) {
        try {
            Page<Candidate> candidates = candidateService.getCandidateByParty(id, page, size);

            if (candidates.getContent().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("candidates", candidates.getContent());
            response.put("currentPage", candidates.getNumber());
            response.put("totalItems", candidates.getTotalElements());
            response.put("totalPages", candidates.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/parliament/{id}/candidate")
    public ResponseEntity<List<Candidate>> getCandidateByParliament(@PathVariable(value = "id") long parliament_id) {
        try {
            Optional<Parliament> parliaments = parliamentService.getParliamentById(parliament_id);

            if(parliaments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                List<Candidate> candidates = candidateService.getCandidateByParliament(parliament_id);
                return new ResponseEntity<>(candidates, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/candidate")
    public ResponseEntity<Map<String, Object>> addCandidate(@RequestParam(name="party_id") long party_id,
                                                            @RequestParam(name="parliament_id") long parliament_id,
                                                            @RequestBody Candidate candidate) {
        try {
            Optional<Party> party = partyService.getPartyById(party_id);
            Optional<Parliament> parliament = parliamentService.getParliamentById(parliament_id);

            if (parliament.isPresent()) {
                candidate.setParty(party.get());
            }

            if (party.isPresent()) {
                candidate.setParliament(parliament.get());
            }

            Map<String, Object> response = new HashMap<>();

            if (candidate.getFullName() != null && candidate.getDisplayName() != null
                    && candidate.getStatus() != null) {
                Candidate c = candidateService.addCandidate(candidate);

                response.put("message", "Data has been inserted.");
                response.put("data", c);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                response.put("message", "Invalid request body: Missing keys. ");
                response.put("data", candidate);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/candidate/{id}")
    public ResponseEntity<Map<String, Object>> updateCandidate(@RequestBody Candidate candidate,
                                                                @PathVariable("id") long candidate_id) {
        Candidate c = candidateService.updateCandidate(candidate, candidate_id);

        Map<String, Object> response = new HashMap<>();

        if(c.getDisplayName() != null && c.getFullName() != null  && c.getStatus() != null) {
            response.put("message", "Data has been updated.");
            response.put("data", c);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Parliament with ID: " + candidate_id + " cannot be found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/candidate/{id}")
    public ResponseEntity<HttpStatus> deleteCandidateById(@PathVariable("id") long candidate_id) {
        try {
            candidateService.deleteCandidate(candidate_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
