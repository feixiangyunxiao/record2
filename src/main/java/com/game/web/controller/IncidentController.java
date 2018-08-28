package com.game.web.controller;

import com.game.common.JsonModel;
import com.game.domain.Incident;
import com.game.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IncidentController {

    @Autowired
    IncidentService incidentService;
    // 展示所有的事件
    @RequestMapping("showAllIncident.do")
    @ResponseBody
    public JsonModel showAllIncident() {
        JsonModel<Incident> resultJsonModel = new JsonModel<>();

        resultJsonModel.setCode(2000);
        resultJsonModel.setMsg("查找成功");
        List<Incident> incidents = incidentService.showAllIncident();
        resultJsonModel.setData(incidents);

        return resultJsonModel;
    }

    // 根据类型展示事件
    @RequestMapping("showAllByType.do")
    @ResponseBody
    public JsonModel showAllByType(String tid) {
        JsonModel<Incident> resultJsonModel = new JsonModel<>();

        resultJsonModel.setCode(2000);
        resultJsonModel.setMsg("查找成功");
        List<Incident> incidents = incidentService.showAllByType(tid);
        resultJsonModel.setData(incidents);

        return resultJsonModel;
    }

    // 添加事件
    @RequestMapping("addIncident.do")
    @ResponseBody
    public JsonModel adIncident(Incident incident) {
        int i = incidentService.addIncident(incident);

        JsonModel<Object> objectJsonModel = JsonModel.creatJson(i > 0 ? 0 : 1, i > 0 ? "添加成功" : "添加失败", null);

        return objectJsonModel;
    }


}
