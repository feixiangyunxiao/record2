package com.game.web.controller;

import com.game.common.JsonModel;
import com.game.domain.Type;
import com.game.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TypeController {

    @Autowired
    TypeService typeService;

    // 展示所有的类型
    @RequestMapping("showAllType.do")
    @ResponseBody
    public JsonModel showAllType() {
        JsonModel<Type> typeJsonModel = new JsonModel<>();

        List<Type> types = typeService.showAllType();

        typeJsonModel.setCode(3000);
        typeJsonModel.setMsg("查询结束");
        typeJsonModel.setData(types);

        return typeJsonModel;
    }

    // 检测type是否重复
    @RequestMapping("checkType.do")
    @ResponseBody
    public JsonModel checkType(String name) {
        int i = typeService.checkType(name);
        JsonModel<Object> objectJsonModel = JsonModel.creatJson(i, i > 0 ? "类型重复" : "类型可用", null);
        return objectJsonModel;
    }

    // 增加类型
    @RequestMapping("addType.do")
    @ResponseBody
    public JsonModel addType(Type type) {

        int i = typeService.addType(type);

        // 在这里大于0才是插入成功的
        JsonModel<Object> objectJsonModel = JsonModel.creatJson(i, i > 0 ? "添加成功" : "添加失败", null);
        return objectJsonModel;
    }

}
