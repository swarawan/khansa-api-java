package com.swarawan.khansapos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "secure_id")
    public String secureId;

    @Column(name = "name")
    public String name;

    @Column(name = "price")
    public Integer price;

    @Column(name = "stock")
    public Integer stock;

    @Column(name = "available")
    public Boolean available;

    @Column(name = "created_at")
    public String createdAt;

    @Column(name = "updated_at")
    public String updatedAt;
}
