package com.edu.uniajc.matricula.controller;

import com.edu.uniajc.matricula.facade.Facade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/login")
@Api(tags = "Login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private Facade facade;

    @PostMapping
    @ApiOperation(value = "Login", httpMethod = "POST", notes = "Este metedo permite logearse")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente Logeado"),
            @ApiResponse(code = 400, message = "Data Invalida"),
            @ApiResponse(code = 500, message = "Error Servidor")
    })
    public ResponseEntity login(@RequestBody String json){
        LOGGER.info("JSE --> Ejecuto el controller login");
        return facade.operation(json);
    }

}
