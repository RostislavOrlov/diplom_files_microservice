package com.example.files_microservice.controller;

import com.example.files_microservice.constants.WsTopics;
import com.example.files_microservice.service.FileService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class FileWsController {

    private final FileService fileService;

    public FileWsController(FileService fileService) {
        this.fileService = fileService;
    }

    @MessageMapping(WsTopics.FILE_CHANGING)
    @SendTo(WsTopics.FILE_HAS_CHANGED)
    public void editFile(Object message) throws Exception {
//        fileService.editFile(message);
    }
}
