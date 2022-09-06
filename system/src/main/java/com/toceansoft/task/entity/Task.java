package com.toceansoft.task.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Task implements Serializable {
    /** 事务列表id */
    private Long id;
    /** 事务列表归属用户id */
    private Long userId;
    /** 事务列表开始日期 */
    private Date startDate;
    /** 事务列表结束日期 */
    private Date endDate;
    /** 事务列表标题 */
    private String title;
    /** 事务列表详细内容 */
    private String detail;
}
