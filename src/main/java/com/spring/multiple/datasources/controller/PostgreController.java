package com.spring.multiple.datasources.controller;

import com.spring.multiple.datasources.model.postgreModel.ActionMsg;
import com.spring.multiple.datasources.repository.postgreRepo.ActionMsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postgre")
public class PostgreController {

    @Autowired
    private ActionMsgRepository actionMsgRepository;

    @RequestMapping(value = "/action/getMsg")
    public ResponseEntity<Object> getActionMsgDetials(@Param("messageId") @RequestParam(value = "messageId",required = false) final String id){
        ActionMsg actionMsg = new ActionMsg();
        Long actionId = Long.parseLong(id);
        actionMsg = actionMsgRepository.findById(actionId);
        return new ResponseEntity<>(actionMsg, HttpStatus.OK);
    }


}
