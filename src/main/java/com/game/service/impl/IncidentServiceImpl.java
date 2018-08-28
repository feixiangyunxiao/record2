package com.game.service.impl;

import com.game.domain.Incident;
import com.game.mapper.IncidentMapper;
import com.game.service.IncidentService;
import com.game.util.DateToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    IncidentMapper incidentMapper;

    @Override
    public List<Incident> showAllIncident() {
        List<Incident> incidents = incidentMapper.selectAllIncident();
        return incidents;
    }

    @Override
    public List<Incident> showAllByType(String tid) {

        List<Incident> incidents = incidentMapper.selectAllByType(Integer.valueOf(tid));

        return incidents;
    }

    @Override
    public int addIncident(Incident incident) {

        int insert = incidentMapper.insert(incident);

        return insert;
    }
}
