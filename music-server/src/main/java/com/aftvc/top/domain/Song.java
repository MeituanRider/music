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
public class Song implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer singerId;

    private String name;

    private String introduction;

    /**
     * 发行时间
     */
      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String pic;

    private String lyric;

    private String url;


}
