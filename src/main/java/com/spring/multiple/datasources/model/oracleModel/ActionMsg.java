package com.spring.multiple.datasources.model.oracleModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "ACTION_MSG")
public class ActionMsg {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TS_CREATED")
    private Timestamp tsCreated;

    @Column(name = "TS_LAST_UPDATED")
    private Timestamp tsLastUpdated;

    @Column(name = "LAST_MODIFIER")
    private String lastModifier;
}
