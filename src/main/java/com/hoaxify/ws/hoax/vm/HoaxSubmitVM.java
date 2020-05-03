package com.hoaxify.ws.hoax.vm;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Created By Yasin Memic on May, 2020
 */
@Data
public class HoaxSubmitVM {


    @Size(min = 1, max = 1000)
    private String content;

    private long attachmentId;

}
