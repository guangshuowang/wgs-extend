package com.wgs.extend.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wgs.extend.demo.entity.pojo.Demo;

/**
 * @Description
 * @Author wanggsh
 * @Date 2020-11-06
 * @Version 1.0
 */
public interface DemoMapper extends BaseMapper<Demo> {

//	Integer insert(Demo demo);
	Demo select(Integer id);
}
