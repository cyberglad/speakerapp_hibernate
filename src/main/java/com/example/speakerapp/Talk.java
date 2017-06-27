package com.example.speakerapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by glady on 27.06.2017.
 */
@Entity
public class Talk {
    @Id
    @GeneratedValue
    private Long talkId;

    private Date when;

    private String title;

    public Talk(){

    }
    public Talk(String title, Date date)
    {
        this.title = title;
        this.when = when;
    }

    public Date getWhen() {
        return when;
    }

    @Override
    public String toString()
    {
        return title;
    }


}
