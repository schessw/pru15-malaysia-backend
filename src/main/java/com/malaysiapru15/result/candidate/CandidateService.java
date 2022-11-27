package com.malaysiapru15.result.candidate;

import com.malaysiapru15.result.parliament.Parliament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Transactional(readOnly = true)
    public List<Candidate> getCandidate() {
        return candidateRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Candidate> getCandidateByFullName(String full_name) {
        List<Candidate> candidates = new ArrayList<>(candidateRepository.findByFullNameContaining(full_name));

        if(candidates.isEmpty()) {
            return new ArrayList<>();
        } else {
            return candidates;
        }
    }

    @Transactional(readOnly = true)
    public List<Candidate> getCandidateByDisplayName(String display_name) {
        List<Candidate> candidates = new ArrayList<>(candidateRepository.findByDisplayNameContaining(display_name));

        if(candidates.isEmpty()) {
            return new ArrayList<>();
        } else {
            return candidates;
        }
    }

    @Transactional(readOnly = true)
    public List<Candidate> getCandidateByStatus(String status) {
        List<Candidate> candidates = new ArrayList<>(candidateRepository.findByStatus(status));

        if(candidates.isEmpty()) {
            return new ArrayList<>();
        } else {
            return candidates;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Candidate> getCandidateById(long id) {
        try {
            return candidateRepository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional(readOnly = true)
    public Page<Candidate> getCandidateByParty(long id, int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            return candidateRepository.findByPartyId(id, paging);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Candidate> getCandidateByParliament(long id) {
        try {
            return candidateRepository.findByParliamentId(id);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate updateCandidate(Candidate candidate, long candidate_id) {
        Optional<Candidate> c = candidateRepository.findById(candidate_id);

        if (c.isPresent()) {
            Candidate _c = c.get();

            _c.setDisplayName(candidate.getDisplayName());
            _c.setFullName(candidate.getFullName());
            _c.setVote(candidate.getVote());
            _c.setStatus(candidate.getStatus());

            return candidateRepository.save(_c);
        } else {
            return new Candidate();
        }
    }

    @Transactional
    public void deleteCandidate(long parliament_id) {
        boolean exists = candidateRepository.existsById(parliament_id);

        if(!exists) {
            throw new IllegalStateException("Parliament with ID = " + parliament_id + " not found.");
        }

        candidateRepository.deleteById(parliament_id);
    }
}
