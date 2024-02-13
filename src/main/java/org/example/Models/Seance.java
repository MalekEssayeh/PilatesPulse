package org.example.Models;

public class Seance {

    private int id;
    private int duration;
    private int coach_id;
    private int event_id;



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
