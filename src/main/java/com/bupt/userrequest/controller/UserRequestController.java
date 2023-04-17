package com.bupt.userrequest.controller;

import com.alibaba.fastjson.JSON;
import com.bupt.userrequest.common.R;
import com.bupt.userrequest.constants.KafkaConsts;
import com.bupt.userrequest.entity.UserRequest;
import com.bupt.userrequest.entity.UserRequestMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


/**
 * @author SoonMachine
 */
@RestController
@RequestMapping("/request")
@Slf4j
@Api(value = "用户请求")
@CrossOrigin
public class UserRequestController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping("userRequest")
    @ApiOperation(value = "用户请求")
    public R userRequest(@RequestPart("files") MultipartFile[] files,  @RequestPart("request") UserRequest request) {
        UserRequestMessage message = new UserRequestMessage().setRequestId(String.valueOf(UUID.randomUUID()))
                .setISPId(request.getISPId())
                .setIdentId(request.getIdentId())
                .setTerminalModel(request.getTerminalModel())
                .setTimeDelay(request.getTimeDelay())
                .setPodName(request.getPodName())
                .setImage(request.getImage())
                .setContainerPort(request.getContainerPort());
        long fileSize = 0;
        int fileNum = files.length;
        for (MultipartFile file : files){
            long size = file.getSize();
            fileSize += size;
        }
        message.setFileNum(String.valueOf(fileNum))
                .setFileSize(String.valueOf(fileSize))
                .setTimeStamp(String.valueOf(System.currentTimeMillis()));
        this.kafkaTemplate.send(KafkaConsts.TOPIC_TEST, JSON.toJSONString(message));
        return R.ok().code(200);
    }
}
