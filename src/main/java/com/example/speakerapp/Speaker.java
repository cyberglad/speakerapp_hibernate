package com.example.speakerapp;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by glady on 27.06.2017.
 */
@Entity
@Table(name="SPEAKER")
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long speakerID;

    public Long getSpeakerID() {
        return speakerID;
    }

    public void setSpeakerID(Long speakerID) {
        this.speakerID = speakerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Talk> talks;

    public Speaker(){}

    public Speaker(String name){this.name = name;}

    public void addTalk(Talk talk) {
        if(talks == null) talks = new HashSet<Talk>();
        talks.add(talk);
    }
    public Set<Talk> getTalks(){return talks;}

    @Override
    public String toString() {
        return "Speaker{" +
                "name='" + name + '\'' +
                '}';
    }

}
