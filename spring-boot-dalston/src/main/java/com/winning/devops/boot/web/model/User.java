package com.winning.devops.boot.web.model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author chensj
 * @title User实体类
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.web.model
 * @date: 2019-04-20 12:07
 */
@Entity
@Table(name = "cloud_user")
public class User {


    private String id;
    private String username;
    private String password;
    private Timestamp createDate;
    private Timestamp lastLoginDate;
    private int status;

    /**
     * idGenerator 这个是hibernate的注解/生成32位UUID
     * @return
     */
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "user_name",nullable = false,unique = true,length = 64,columnDefinition = "varchar(64) comment '用户名'")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "password",nullable = true,length = 128,columnDefinition = "varchar(128) comment '密码'")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * insertable insert 是否可以
     * updatable  update 是否可以
     * @return
     */
    @Column(name = "create_date",nullable = false,insertable = false,updatable = false,columnDefinition = "timestamp default CURRENT_TIMESTAMP comment '创建时间'")
    @Generated(GenerationTime.INSERT)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    @Column(name = "login_date",nullable = true,insertable = false,updatable = true,columnDefinition = "timestamp comment '登陆时间'")
    @Generated(GenerationTime.ALWAYS)
    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Column(insertable = false,updatable = true,columnDefinition = "tinyint default 0 comment '用户状态'")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
