package com.game.mapper;

import com.game.domain.Incident;

import java.util.List;

public interface IncidentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Incident record);

    int insertSelective(Incident record);

    Incident selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Incident record);

    int updateByPrimaryKey(Incident record);

    List<Incident> selectAllIncident();

    List<Incident> selectAllByType(int tid);
}