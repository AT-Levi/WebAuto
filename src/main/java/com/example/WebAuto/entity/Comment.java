package com.example.WebAuto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Comment extends BaseEntity{

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    private String objectType;

    @Column(nullable = false)
    private Long objectId;
}
