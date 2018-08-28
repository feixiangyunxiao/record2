package com.game.service;

import com.game.domain.Type;

import java.util.List;

public interface TypeService {

    List<Type> showAllType();

    int addType(Type type);

    int checkType(String name);
}
