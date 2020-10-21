package com.epidemiologicSurvey.bean;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("user")
public class User {

    @Id
    private long id;
}
