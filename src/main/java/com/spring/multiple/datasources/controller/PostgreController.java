package com.spring.multiple.datasources.controller;

import com.spring.multiple.datasources.model.postgreModel.ActionMsg;
import com.spring.multiple.datasources.service.PostgreDatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/postgre")
public class PostgreController {

//    @Autowired
//    private ActionMsgRepository actionMsgRepository;

    @Autowired
    private PostgreDatasourceService postgreDatasourceService;

    private final static java.util.logging.Logger Log = Logger.getLogger(PostgreController.class.getName());

    @RequestMapping(value = "/action/getMsg")
    public ResponseEntity<Object> getActionMsgDetails(@Param("messageId") @RequestParam(value = "messageId",required = false) final String id){
        ActionMsg actionMsg = postgreDatasourceService.getDetailsOfAction(id);
        return new ResponseEntity<>(actionMsg,HttpStatus.OK);
    }

}
