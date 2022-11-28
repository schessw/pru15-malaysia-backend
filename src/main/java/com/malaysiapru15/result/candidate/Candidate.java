package com.malaysiapru15.result.candidate;

import com.malaysiapru15.result.parliament.Parliament;
import com.malaysiapru15.result.party.Party;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private long id;

    @NotNull
    @Column(name = "candidate_full_name")
    private String full_name;

    @NotNull
    @Column(name = "candidate_display_name")
    private String display_name;

    @NotNull
    @Column(name = "candidate_vote")
    private int vote;

    @NotNull
    @Column(name = "candidate_status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "party_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Party party;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "parliament_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Parliament parliament;

    public Candidate() {

    }

    public Candidate(long id, String full_name, String display_name, int vote, String status, Party party, Parliament parliament) {
        this.id = id;
        this.full_name = full_name;
        this.display_name = display_name;
        this.vote = vote;
        this.status = status;
        this.party = party;
        this.parliament = parliament;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public String getDisplayName() {
        return display_name;
    }

    public void setDisplayName(String display_name) {
        this.display_name = display_name;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Parliament getParliament() {
        return parliament;
    }

    public void setParliament(Parliament parliament) {
        this.parliament = parliament;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", display_name='" + display_name + '\'' +
                ", vote=" + vote +
                ", status='" + status + '\'' +
                ", party=" + party +
                ", parliament=" + parliament +
                '}';
    }
}
