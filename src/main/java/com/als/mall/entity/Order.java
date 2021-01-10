package com.als.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@TableName("orders")
public class Order {
	
	@TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String loginName;
    private String userAddress;

    /**
     * 总金额
     */
    private Float cost;
    
    /**
	 * 订单号
	 */
    private String serialnumber;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
	private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
