package com.aftvc.top.dao;

import com.aftvc.top.domain.SystemLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface SystemLogMapper extends BaseMapper<SystemLog> {
    void saveSysLog(SystemLog sysLog);
    List<SystemLog> queryAllLogs();
}
