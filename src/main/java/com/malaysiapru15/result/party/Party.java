package com.malaysiapru15.result.party;

import javax.persistence.*;

@Entity
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long id;

    @Column(name = "party_name")
    private String name;

    public Party() {

    }

    public Party(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Party{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
