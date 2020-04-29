package com.hoaxify.ws.user.vm;

import com.hoaxify.ws.shared.FileType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@Data
public class UserUpdateVM {

    @NotNull(message = "{hoaxify.constraint.displayName.NotNull.message}")
    @Size(min = 4, max = 255)
    private String displayName;

    @FileType(types = {"jpeg", "png"})
    private String image;
}
