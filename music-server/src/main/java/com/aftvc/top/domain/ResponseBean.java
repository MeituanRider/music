package com.aftvc.top.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ Author     ：Yan
 * @ Date       ：Created in 0:10 2020/7/9
 * @ Description：消息返回类Result
 * @ Modified By：
 * @Version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean implements Serializable {

    private Integer code;
    private String msg;
    private Object userMsg;
}
