package com.esprit.pilatespulse.pilatespulsev3.Interfaces;

import java.util.List;

public interface IEventService<T> {

    public void addNewEvent(T t);
    public List<T> displayEvents();
    public void updateEvent(T t, int id);
    public void deleteEvent(int id);

}
