package com.ticlab.demo.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

import javax.persistence.*

@Entity
class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String name
    Integer age

    @Column(length = 1)
    String sex

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false)
    Date createdAt

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(nullable = true)
    Date updatedAt
}
