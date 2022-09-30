package com.example.SpringWebfluxMongo.controller;

import com.example.SpringWebfluxMongo.service.ChessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class ChessController {
    @Autowired
    ChessService chessService;

    @PostMapping("user/{userId}")
    public void uploadUserData(@PathVariable("userId") String userId) {
        chessService.uploadDataToDb(userId);

    }
    @PostMapping("ipsum")
    public void showIpsum(){
        chessService.showIpsum();

    }

}
