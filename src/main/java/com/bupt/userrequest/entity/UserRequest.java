package com.bupt.userrequest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author SoonMachine
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户请求实体", description = "user request")
public class UserRequest implements Serializable {


    private static final long serialVersionUID = -7104947730079432902L;

    @ApiModelProperty(value = "ISPId", example ="baidu")
    private String ISPId = String.valueOf(UUID.randomUUID());

    @ApiModelProperty(value = "IdentId", example ="7777777")
    private String IdentId = String.valueOf(UUID.randomUUID());

    @ApiModelProperty(value = "TerminalModel", example ="tesla_model_3")
    private String TerminalModel;

    @ApiModelProperty(value = "TimeDelay", example ="1s")
    private String TimeDelay;

    @ApiModelProperty(value = "pod名称", example ="lpr")
    private String podName;

    @ApiModelProperty(value = "容器image", example ="soonmachine/lpr:v1")
    private String image;

    @ApiModelProperty(value = "容器运行端口号", example ="5000")
    private Integer containerPort;

}
