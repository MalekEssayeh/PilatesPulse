package org.example.Models;

import java.awt.*;
import java.util.List;

public class Seance {

    private int id;
    private int duration;
    private int coach_id;
    private int event_id;
    private List<User> seance_participants;

    public Seance(int duration, int coach_id, int event_id, List<User> seance_participants) {
        this.duration = duration;
        this.coach_id = coach_id;
        this.event_id = event_id;
        this.seance_participants = seance_participants;
    }

    public List<User> getEvent_participants() {
        return seance_participants;
    }

    public void setEvent_participants(List<User> event_participants) {
        this.seance_participants = event_participants;
    }

    public Seance(int duration, int coach_id, int event_id ) {
        this.duration = duration;
        this.coach_id = coach_id;
        this.event_id = event_id;
    }
    public Seance() {
    }

    public Seance(int duration, int coach_id) {
        this.duration = duration;
        this.coach_id = coach_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(int coach_id) {
        this.coach_id = coach_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", duration=" + duration +
                ", coach_id=" + coach_id +
                ", event_id=" + event_id +
                '}';
    }
}
