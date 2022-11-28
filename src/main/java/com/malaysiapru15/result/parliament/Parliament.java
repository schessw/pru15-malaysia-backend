package com.malaysiapru15.result.parliament;

import com.malaysiapru15.result.state.State;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parliament")
public class Parliament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parliament_id")
    private long id;

    @NotNull
    @Column(name = "parliament_name")
    private String name;

    @NotNull
    @Column(name = "parliament_code")
    private String code;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private State state;

    public Parliament() {

    }

    public Parliament(long parliament_id, String parliament_name, String parliament_code, State state) {
        this.id = parliament_id;
        this.name = parliament_name;
        this.code = parliament_code;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public State getState() {
        return state;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Parliament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", state=" + state +
                '}';
    }
}
