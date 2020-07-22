package com.aftvc.top.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Consumer implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;
    //Byte字节方便后面转换
    private Byte sex;

    private String phoneNum;

    private String email;

      @TableField(fill = FieldFill.INSERT)
    private Date birth;

    private String introduction;

    private String location;

    private String avator;

      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
