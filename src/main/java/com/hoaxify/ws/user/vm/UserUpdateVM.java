package com.hoaxify.ws.user.vm;

import com.hoaxify.ws.shared.FileType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Data
@ApiModel(value = "model for creating response for update processing")
public class UserUpdateVM {

    @ApiModelProperty(value = "Updated User Display Name")
    @NotNull(message = "{hoaxify.constraint.displayName.NotNull.message}")
    @Size(min = 4, max = 255)
    private String displayName;

    @ApiModelProperty(value = "Updated User Image")
    @FileType(types = {"jpeg", "png"})
    private String image;
}
