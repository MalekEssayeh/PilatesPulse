package com.esprit.pilatespulse.pilatespulsev3.Interfaces;

import java.util.List;

public interface ISessionService<T> {

    public void addSession(T t);
    public List<T> displaySessions();
    public void updateSession(T t, int id);
    public void deleteSession(int id);

}
