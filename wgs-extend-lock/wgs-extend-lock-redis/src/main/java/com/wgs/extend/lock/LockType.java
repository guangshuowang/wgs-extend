package com.wgs.extend.lock;


import org.springframework.util.StringUtils;

/**
 * @Description
 * @ClassName：LockType
 * @Author wanggsh
 * @Date 2020年09月09日 16:45
 * @Version 1.0
 */
public enum LockType {

	SYS_ITEM("sys_item", "系统商品锁"),
	SYS_INTEGRAL_ITEM("sys_integral_item", "系统积分商品锁"),
	SYS_ORDER("sys_order", "系统订单锁"),
	SYS_BALANCE_ACCOUNT("sys_balance_account", "系统余额账户锁"),
	SYS_INTEGRAL_ACCOUNT("sys_integral_account", "系统积分账户锁"),
	;

	public String type;
	public String name;

	LockType(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public static String getName(String type) {
		if (StringUtils.isEmpty(type)) {
			return null;
		}
		for (LockType lockType : values()) {
			if (lockType.type.equals(type)) {
				return lockType.name;
			}
		}
		return null;
	}
}
