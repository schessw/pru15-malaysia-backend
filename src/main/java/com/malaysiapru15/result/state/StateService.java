package com.malaysiapru15.result.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateService {
    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Transactional(readOnly = true)
    public List<State> getState() {
        return stateRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<State> getState(String state_name) {
        List<State> states = new ArrayList<>(stateRepository.findByNameContaining(state_name));

        if(states.isEmpty()) {
            return new ArrayList<>();
        } else {
            return states;
        }
    }

    @Transactional(readOnly = true)
    public Optional<State> getStateById(long id) {
        try {
            return stateRepository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public State addState(State state) {
        return stateRepository.save(state);
    }

    @Transactional
    public State updateState(State state, long state_id) {
        Optional<State> s = stateRepository.findById(state_id);

        if (s.isPresent()) {
            State _s = s.get();
            _s.setName(state.getName());
            return stateRepository.save(_s);
        } else {
            return new State();
        }
    }

    @Transactional
    public void deleteState(long state_id) {
        boolean exists = stateRepository.existsById(state_id);

        if(!exists) {
            throw new IllegalStateException("State with ID = " + state_id + " not found.");
        }

        stateRepository.deleteById(state_id);
    }
}
