package com.spring.multiple.datasources.controller;

import com.spring.multiple.datasources.model.oracleModel.ActionMsg;
import com.spring.multiple.datasources.repository.oracleRepo.ActionRepository;
import com.spring.multiple.datasources.service.OracleDatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private OracleDatasourceService oracleDatasourceService;

    private final static Logger Log = Logger.getLogger(OracleController.class.getName());


    @RequestMapping(value = "/action/getMsg", method = RequestMethod.GET)
    public ResponseEntity<Object> getActionMsgDetails(@RequestParam(value = "messageId",required = false) String id){
        ActionMsg actionMsg = oracleDatasourceService.getDetailsAction(id);
        return new ResponseEntity<>(actionMsg,HttpStatus.OK);
    }
}
