package com.spring.multiple.datasources.controller;

import com.spring.multiple.datasources.model.oracleModel.ActionMsg;
import com.spring.multiple.datasources.repository.oracleRepo.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/oracle")
public class OracleController {

    private final static Logger Log = Logger.getLogger(OracleController.class.getName());
    @Autowired
    private ActionRepository actionRepository;

    @RequestMapping(value = "/action/getMsg", method = RequestMethod.GET)
    public ResponseEntity<Object> getActionMsgDetails(@RequestParam(value = "messageId",required = false) String id){
        ActionMsg actionMsg = new ActionMsg();
        Long actionId= Long.parseLong(id);
        actionMsg = actionRepository.findById(actionId);
        if(actionMsg!=null){
            Log.info("********INSIDE*********");
            return new ResponseEntity<>(actionMsg, HttpStatus.OK);
        }else {
            Log.info("********OUTSIDE*******");
            return new ResponseEntity<>(null);
        }

    }
}
