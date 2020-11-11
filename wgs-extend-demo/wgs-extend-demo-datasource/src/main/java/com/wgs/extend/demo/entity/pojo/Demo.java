package com.wgs.extend.demo.entity.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description
 * @ClassName：Demo
 * @Author wanggsh
 * @Date 2020年11月06日 16:56
 * @Version 1.0
 */
@Getter
@Setter
public class Demo implements Serializable {

	private Integer id;
	private String name;

	@Override
	public String toString() {
		return name;
	}
}
