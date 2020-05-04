package com.hoaxify.ws.hoax.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Created By Yasin Memic on May, 2020
 */
@Data
@ApiModel(value = "Model for Hoax Submit")
public class HoaxSubmitVM {


    @ApiModelProperty(required = true, value = "Hoax Content - Submit Processing")
    @Size(min = 1, max = 1000)
    private String content;

    @ApiModelProperty(required = true, value = "Hoax Attachment ID - Submit Processing")
    private long attachmentId;

}
