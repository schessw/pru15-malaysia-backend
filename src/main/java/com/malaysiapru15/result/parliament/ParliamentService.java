package com.malaysiapru15.result.parliament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParliamentService {
    private final ParliamentRepository parliamentRepository;

    @Autowired
    public ParliamentService(ParliamentRepository parliamentRepository) {
        this.parliamentRepository = parliamentRepository;
    }

    @Transactional(readOnly = true)
    public List<Parliament> getParliament() {
        return parliamentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Parliament> getParliamentByName(String parliament_name) {
        List<Parliament> parliaments = new ArrayList<>(parliamentRepository.findByNameContaining(parliament_name));

        if(parliaments.isEmpty()) {
            return new ArrayList<>();
        } else {
            return parliaments;
        }
    }

    @Transactional(readOnly = true)
    public List<Parliament> getParliamentByCode(String parliament_code) {
        List<Parliament> parliaments = new ArrayList<>(parliamentRepository.findByCodeContaining(parliament_code));

        if(parliaments.isEmpty()) {
            return new ArrayList<>();
        } else {
            return parliaments;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Parliament> getParliamentById(long id) {
        try {
            return parliamentRepository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional(readOnly = true)
    public List<Parliament> getParliamentByState(long id) {
        try {
            return parliamentRepository.findByStateId(id);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public Parliament addParliament(Parliament parliament) {
        return parliamentRepository.save(parliament);
    }

    @Transactional
    public Parliament updateParliament(Parliament parliament, long parliament_id) {
        Optional<Parliament> p = parliamentRepository.findById(parliament_id);

        if (p.isPresent()) {
            Parliament _p = p.get();

            if (Objects.nonNull(parliament.getName()) && !"".equalsIgnoreCase(parliament.getName())) {
                _p.setName(parliament.getName());
            }

            if (Objects.nonNull(parliament.getCode()) && !"".equalsIgnoreCase(parliament.getCode())) {
                _p.setCode(parliament.getCode());
            }

            return parliamentRepository.save(_p);
        } else {
            return new Parliament();
        }
    }

    @Transactional
    public void deleteParliament(long parliament_id) {
        boolean exists = parliamentRepository.existsById(parliament_id);

        if(!exists) {
            throw new IllegalStateException("Parliament with ID = " + parliament_id + " not found.");
        }

        parliamentRepository.deleteById(parliament_id);
    }
}
