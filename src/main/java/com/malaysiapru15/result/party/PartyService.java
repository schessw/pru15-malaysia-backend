package com.malaysiapru15.result.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PartyService {
    private final PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public List<Party> getParty() {
        return partyRepository.findAll();
    }

    public List<Party> getParty(String party_name) {
        List<Party> parties = new ArrayList<Party>();
        partyRepository.findByNameContaining(party_name).forEach(parties::add);

        if(parties.isEmpty()) {
            return new ArrayList<Party>();
        } else {
            return parties;
        }
    }

    public Optional<Party> getPartyById(long id) {
        Optional<Party> parties = partyRepository.findById(id);

        return parties;
    }

    public Party addParty(Party party) {
        return partyRepository.save(party);
    }

    @Transactional
    public Party updateParty(Party party, Long party_id) {
        Party p = partyRepository.findById(party_id).get();

        if (Objects.nonNull(party.getName()) && !"".equalsIgnoreCase(party.getName())) {
            p.setName(party.getName());
        }

        return partyRepository.save(p);
    }

    public void deleteParty(Long party_id) {
        boolean exists = partyRepository.existsById(party_id);

        if(!exists) {
            throw new IllegalStateException("Party with ID = " + party_id + " not found.");
        }

        partyRepository.deleteById(party_id);
    }
}
