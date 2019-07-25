package com.htsc.zcgl.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by admin on 2018/12/19.
 */
@Getter
@Setter
@Table(name = "test")
@Entity(name = "Test")
public class Test {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
