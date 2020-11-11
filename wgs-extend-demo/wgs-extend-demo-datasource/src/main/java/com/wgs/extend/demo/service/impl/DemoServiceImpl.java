package com.wgs.extend.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wgs.extend.demo.entity.pojo.Demo;
import com.wgs.extend.demo.mapper.DemoMapper;
import com.wgs.extend.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @ClassName：DemoServiceImpl
 * @Author wanggsh
 * @Date 2020年11月06日 16:43
 * @Version 1.0
 */
@Slf4j
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

//	@Autowired
//	private DemoMapper demoMapper;

	@Override
	public String get(String dataSourceId) {
		log.info("查询数据库：{}", dataSourceId);
		Demo select = baseMapper.select(1);
		return select == null ? "" : select.toString();
	}
}
