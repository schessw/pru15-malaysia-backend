package com.malaysiapru15.result.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StateService {
    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<State> getState() {
        return stateRepository.findAll();
    }

    public List<State> getState(String state_name) {
        List<State> states = new ArrayList<State>();
        stateRepository.findByNameContaining(state_name).forEach(states::add);

        if(states.isEmpty()) {
            return new ArrayList<State>();
        } else {
            return states;
        }
    }

    public Optional<State> getStateById(long id) {
        Optional<State> states = stateRepository.findById(id);

        return states;
    }

    public State addState(State state) {
        return stateRepository.save(state);
    }

    @Transactional
    public State updateState(State state, Long state_id) {
        State s = stateRepository.findById(state_id).get();

        if (Objects.nonNull(state.getName()) && !"".equalsIgnoreCase(state.getName())) {
            s.setName(state.getName());
        }

        return stateRepository.save(s);
    }

    public void deleteState(Long state_id) {
        boolean exists = stateRepository.existsById(state_id);

        if(!exists) {
            throw new IllegalStateException("State with ID = " + state_id + " not found.");
        }

        stateRepository.deleteById(state_id);
    }
}
