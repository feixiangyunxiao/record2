package com.game.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Incident {
    private Integer id;

    private String name;

    private Integer uid;

    private Integer tid;


    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date occurredtime;

    private Integer flag;

    private Integer repeated;

    private String reserve;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    public Date getOccurredtime() {
        return occurredtime;
    }

    public void setOccurredtime(Date occurredtime) {
        this.occurredtime = occurredtime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getRepeated() {
        return repeated;
    }

    public void setRepeated(Integer repeated) {
        this.repeated = repeated;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve == null ? null : reserve.trim();
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uid=" + uid +
                ", tid=" + tid +
                ", createtime=" + createtime +
                ", occurredtime=" + occurredtime +
                ", flag=" + flag +
                ", repeated=" + repeated +
                ", reserve='" + reserve + '\'' +
                '}';
    }
}