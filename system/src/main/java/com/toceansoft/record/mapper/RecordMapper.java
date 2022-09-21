package com.toceansoft.record.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.toceansoft.record.entity.Record;

/**
 * 销售员沟通记录Mapper接口
 * 
 * @author chan_juhin
 * @date Mon Sep 19 10:39:15 CST 2022
 */
 @Mapper
public interface RecordMapper 
{
    /**
     * 查询销售员沟通记录
     * 
     * @param id 销售员沟通记录ID
     * @return 销售员沟通记录
     */
    public Record selectRecordById(Long id);

    /**
     * 查询销售员沟通记录列表
     * 
     * @param record 销售员沟通记录
     * @return 销售员沟通记录集合
     */
    public List<Record> selectRecordList(Record record);

    /**
     * 新增销售员沟通记录
     * 
     * @param record 销售员沟通记录
     * @return 结果
     */
    public int insertRecord(Record record);

    /**
     * 修改销售员沟通记录
     * 
     * @param record 销售员沟通记录
     * @return 结果
     */
    public int updateRecord(Record record);

    /**
     * 删除销售员沟通记录
     * 
     * @param id 销售员沟通记录ID
     * @return 结果
     */
    public int deleteRecordById(Long id);

    /**
     * 批量删除销售员沟通记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRecordByIds(Long[] ids);
}
