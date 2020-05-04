package com.hoaxify.ws.user.vm;

import com.hoaxify.ws.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Data
@ApiModel(value = "model for creating response")
public class UserVM {
    @ApiModelProperty(required = true, value = "User Username")
    private String username;
    @ApiModelProperty(required = true, value = "User Display Name")
    private String displayName;
    @ApiModelProperty(required = true, value = "User Image")
    private String image;

    public UserVM(User user) {
        this.setUsername(user.getUsername());
        this.setDisplayName(user.getDisplayName());
        this.setImage(user.getImage());
    }
}
