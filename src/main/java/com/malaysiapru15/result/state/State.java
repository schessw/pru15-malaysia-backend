package com.malaysiapru15.result.state;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "state")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private long id;

    @NotNull
    @Column(name = "state_name")
    private String name;

    public State() {

    }

    public State(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "State{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
