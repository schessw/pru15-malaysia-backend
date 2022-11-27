package com.malaysiapru15.result.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartyService {
    private final PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @Transactional(readOnly = true)
    public List<Party> getParty() {
        return partyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Party> getParty(String party_name) {
        List<Party> parties = new ArrayList<>(partyRepository.findByNameContaining(party_name));

        if(parties.isEmpty()) {
            return new ArrayList<>();
        } else {
            return parties;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Party> getPartyById(long id) {
        try {
            return partyRepository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public Party addParty(Party party) {
        return partyRepository.save(party);
    }

    @Transactional
    public Party updateParty(Party party, long party_id) {
        Optional<Party> p = partyRepository.findById(party_id);

        if (p.isPresent()) {
            Party _p = p.get();
            _p.setName(party.getName());
            return partyRepository.save(_p);
        } else {
            return new Party();
        }
    }

    @Transactional
    public void deleteParty(long party_id) {
        boolean exists = partyRepository.existsById(party_id);

        if(!exists) {
            throw new IllegalStateException("Party with ID = " + party_id + " not found.");
        }

        partyRepository.deleteById(party_id);
    }
}
