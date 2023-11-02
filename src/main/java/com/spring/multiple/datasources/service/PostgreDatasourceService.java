package com.spring.multiple.datasources.service;

import com.spring.multiple.datasources.model.postgreModel.ActionMsg;
import com.spring.multiple.datasources.repository.postgreRepo.ActionMsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostgreDatasourceService {

    @Autowired
    private final ActionMsgRepository actionMsgRepository;

    public PostgreDatasourceService(ActionMsgRepository actionMsgRepository) {
        this.actionMsgRepository = actionMsgRepository;
    }

    public ActionMsg getDetailsOfAction(String id){
        Long longId = Long.parseLong(id);
        ActionMsg actionMsg = actionMsgRepository.findById(longId);
        if(actionMsg!=null){
            return actionMsg;
        }
        return null;
    }
}
