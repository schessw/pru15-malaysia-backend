package com.malaysiapru15.result.parliament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ParliamentService {
    private final ParliamentRepository parliamentRepository;

    @Autowired
    public ParliamentService(ParliamentRepository parliamentRepository) {
        this.parliamentRepository = parliamentRepository;
    }
    public List<Parliament> getParliaments() {
        return parliamentRepository.findAll();
    }

    public Parliament addParliament(Parliament parliament) {
        return parliamentRepository.save(parliament);
    }

    @Transactional
    public Parliament updateParliament(Parliament parliament, Long parliament_id) {
        Parliament p = parliamentRepository.findById(parliament_id).get();

        if (Objects.nonNull(parliament.getParliament_code()) && !"".equalsIgnoreCase(parliament.getParliament_code())) {
            p.setParliament_code(parliament.getParliament_code());
        }

        if (Objects.nonNull(parliament.getParliament_name()) && !"".equalsIgnoreCase(parliament.getParliament_name())) {
            p.setParliament_name(parliament.getParliament_name());
        }

        if (Objects.nonNull(parliament.getState_id())) {
            p.setState_id(parliament.getState_id());
        }

        return parliamentRepository.save(p);
    }

    public void deleteParliamentById(Long parliament_id) {
        boolean exists = parliamentRepository.existsById(parliament_id);
        if(!exists) {
            throw new IllegalStateException("Parliament with ID = " + parliament_id + " not found.");
        }
        parliamentRepository.deleteById(parliament_id);
    }
}
