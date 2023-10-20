package com.spring.multiple.datasources.model.postgreModel;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "ACTION_MSG")
public class ActionMsg {
    @Id
    @Column(name = "ID")
    private Long id;

//    @Lob
//    @Column(name= "message_xml")
//    private Long messageXML;

    @Column(name = "TS_CREATED")
    private Timestamp tsCreated;

    @Column(name = "TS_LAST_UPDATED")
    private Timestamp tsLastUpdated;

    @Column(name = "LAST_MODIFIER")
    private String lastModifier;
}
