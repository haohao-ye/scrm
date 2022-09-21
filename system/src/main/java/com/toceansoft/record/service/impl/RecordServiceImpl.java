package com.toceansoft.record.service.impl;

import java.util.List;
import com.toceansoft.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toceansoft.record.mapper.RecordMapper;
import com.toceansoft.record.entity.Record;
import com.toceansoft.record.service.IRecordService;

/**
 * 销售员沟通记录Service业务层处理
 * 
 * @author chan_juhin
 * @date Mon Sep 19 10:39:15 CST 2022
 */
@Service
public class RecordServiceImpl implements IRecordService 
{
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 查询销售员沟通记录
     * 
     * @param id 销售员沟通记录ID
     * @return 销售员沟通记录
     */
    @Override
    public Record selectRecordById(Long id)
    {
        return recordMapper.selectRecordById(id);
    }

    /**
     * 查询销售员沟通记录列表
     * 
     * @param record 销售员沟通记录
     * @return 销售员沟通记录
     */
    @Override
    public List<Record> selectRecordList(Record record)
    {
        return recordMapper.selectRecordList(record);
    }

    /**
     * 新增销售员沟通记录
     * 
     * @param record 销售员沟通记录
     * @return 结果
     */
    @Override
    public int insertRecord(Record record)
    {
        record.setCreateTime(DateUtils.getNowDate());
        return recordMapper.insertRecord(record);
    }

    /**
     * 修改销售员沟通记录
     * 
     * @param record 销售员沟通记录
     * @return 结果
     */
    @Override
    public int updateRecord(Record record)
    {
        record.setUpdateTime(DateUtils.getNowDate());
        return recordMapper.updateRecord(record);
    }

    /**
     * 批量删除销售员沟通记录
     * 
     * @param ids 需要删除的销售员沟通记录ID
     * @return 结果
     */
    @Override
    public int deleteRecordByIds(Long[] ids)
    {
        return recordMapper.deleteRecordByIds(ids);
    }

    /**
     * 删除销售员沟通记录信息
     * 
     * @param id 销售员沟通记录ID
     * @return 结果
     */
    @Override
    public int deleteRecordById(Long id)
    {
        return recordMapper.deleteRecordById(id);
    }
}
