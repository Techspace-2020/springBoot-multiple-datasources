package com.spring.multiple.datasources.repository.oracleRepo;

import com.spring.multiple.datasources.model.oracleModel.ActionMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<ActionMsg,String> {

    ActionMsg findById(Long id);
    ActionMsg save(ActionMsg actionMsg);
}
