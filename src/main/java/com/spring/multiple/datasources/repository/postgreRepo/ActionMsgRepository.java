package com.spring.multiple.datasources.repository.postgreRepo;

import com.spring.multiple.datasources.model.postgreModel.ActionMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionMsgRepository extends JpaRepository<ActionMsg,String> {
    ActionMsg findById(Long id);
    ActionMsg save(ActionMsg actionMsg);
}
