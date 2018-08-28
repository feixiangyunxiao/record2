package com.game.service;

import com.game.domain.Incident;

import java.util.List;

public interface IncidentService {

    List<Incident> showAllIncident();

    List<Incident> showAllByType(String tid);

    int addIncident(Incident incident);
}
