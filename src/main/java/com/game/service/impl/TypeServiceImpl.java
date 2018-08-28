package com.game.service.impl;

import com.game.domain.Type;
import com.game.mapper.TypeMapper;
import com.game.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Override
    public List<Type> showAllType() {
        List<Type> types = typeMapper.selectAll();
        return types;
    }

    @Override
    public int addType(Type type) {
        int insert = 0;
        Type typeChecked = typeMapper.selectByName(type.getName());
        if (typeChecked == null) {
            insert = typeMapper.insert(type);
        }
        return insert;
    }

    @Override
    public int checkType(String name) {
        Type type = typeMapper.selectByName(name);
        if (type == null) {
            return 0;
        } else {
            return 1;
        }
    }
}
