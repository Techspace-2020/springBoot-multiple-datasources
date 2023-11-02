package com.spring.multiple.datasources.service;

import com.spring.multiple.datasources.model.oracleModel.ActionMsg;
import com.spring.multiple.datasources.repository.oracleRepo.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OracleDatasourceService {

    @Autowired
    private ActionRepository actionRepository;

    public ActionMsg getDetailsAction(String id){
        Long longId = Long.parseLong(id);
        ActionMsg actionMsg = actionRepository.findById(longId);
        if(actionMsg!=null){
            return actionMsg;
        }
        return null;
    }
}
