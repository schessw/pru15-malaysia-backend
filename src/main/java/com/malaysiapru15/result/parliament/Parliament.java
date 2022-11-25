package com.malaysiapru15.result.parliament;

import javax.persistence.*;

@Entity
@Table(name="parliament")
public class Parliament {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long parliament_id;
    @Column(name="parliament_name")
    private String parliament_name;  // Name of the seat
    @Column(name="parliament_code")
    private String parliament_code;  //
    @Column(name="state_id")
    private int state_id;

    public Parliament() {

    }

    public Parliament(Long parliament_id, String parliament_name, String parliament_code, int state_id) {
        this.parliament_id = parliament_id;
        this.parliament_name = parliament_name;
        this.parliament_code = parliament_code;
        this.state_id = state_id;
    }

    public Long getParliament_id() {
        return parliament_id;
    }

    public String getParliament_name() {
        return parliament_name;
    }

    public String getParliament_code() {
        return parliament_code;
    }

    public int getState_id() {
        return state_id;
    }

    public void setParliament_id(Long parliament_id) {
        this.parliament_id = parliament_id;
    }

    public void setParliament_name(String parliament_name) {
        this.parliament_name = parliament_name;
    }

    public void setParliament_code(String parliament_code) {
        this.parliament_code = parliament_code;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    @Override
    public String toString() {
        return "parliament_name=" + parliament_name + "; parliament_code=" + parliament_code +
                "; state_id=" + state_id;
    }
}
