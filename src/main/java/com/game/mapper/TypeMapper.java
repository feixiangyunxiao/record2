package com.game.mapper;

import com.game.domain.Type;

import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Type> selectAll();

    Type selectByName(String name);

    int insert(Type record);
}