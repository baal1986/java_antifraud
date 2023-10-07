package com.bank.antifraud.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;


@Table(name = "audit", schema = "anti_fraud")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 40)
    @Column(name = "entity_type", nullable = false)
    @NotNull(message = "entity_type can't be null")
    private String entityType;

    @Size(max = 255)
    @Column(name = "operation_type", nullable = false)
    @NotNull(message = "operation_type can't be null")
    private String operationType;

    @Size(max = 255)
    @Column(name = "created_by", nullable = false)
    @NotNull(message = "created_by can't be null")
    private String createdBy;

    @Size(max = 255)
    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_at", nullable = false)
    @NotNull(message = "created_at can't be null")
    private Timestamp createdAt;
    
    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "new_entity_json")
    private String newEntityJson;

    @Column(name = "entity_json", nullable = false)
    @NotNull(message = "entity_json can't be null")
    private String entityJson;
}
