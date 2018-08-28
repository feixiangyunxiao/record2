package com.game.mapper;

import com.game.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByEmail(String name);

    int updateByEmail(@Param("email")String email, @Param("activecode")String activecode);

    User selectByEmailAndResetcode(@Param("email")String email, @Param("resetcode")String resetcode);

    int updatePasswordByEmail(@Param("email")String email, @Param("password")String password);

}