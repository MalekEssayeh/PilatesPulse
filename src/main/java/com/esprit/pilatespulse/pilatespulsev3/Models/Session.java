package com.esprit.pilatespulse.pilatespulsev3.Models;

import java.sql.Date;
import java.sql.Time;

public class Session {

    private int sessionID;
    private String name;
    private String description;
    private int duration;
    private int coachID;
    private int eventID;
    private Time startTime;
    private Time finishTime;
    private Date date;

    public Session() {
    }

    public Session(int sessionID, String name, String description, int duration, int coachID, int eventID, Time startTime, Time finishTime, Date date) {
        this.sessionID = sessionID;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.coachID = coachID;
        this.eventID = eventID;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.date = date;
    }

    public Session(String name, String description, int duration, int coachID, int eventID, Time startTime, Time finishTime, Date date) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.coachID = coachID;
        this.eventID = eventID;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.date = date;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCoachID() {
        return coachID;
    }

    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Time finishTime) {
        this.finishTime = finishTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionID=" + sessionID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", coachID=" + coachID +
                ", eventID=" + eventID +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", date=" + date +
                '}';
    }

}
