package com.wgs.extend.common.utils;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @类功能说明： String字符串工具类.
 */

public final class StringUtils {

	public static boolean isChinaMobile(String mobile) {
		if(mobile == null){
			return false;
		}
		if(mobile.length() != 11){
			return false;
		}
		/**
		 * "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,
		 * [^4]表示除4以外的任何一个,\\d{9}"代表后面是可以是0～9的数字，有9位。
		 */
		String regExp = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}


	/**
	 * 把int类型的num转成三位长度的字符串
	 * @param num
	 * @return
	 */
	public static String getTwo(Integer num) {
		if (num < 10) {
			return "0" + num;
		} else if (num < 100) {
			return String.valueOf(num);
		} else {
			return String.valueOf(num).substring(0,2);
		}
	}

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim()) || "null".equals(str) || "undefined".equals(str);
    }


    /**
     * 获取去掉横线的长度为32的UUID串.
     * 
     * @return uuid.
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

	public static String get16UUID() {
		String uuid= UUID.randomUUID().toString();
		char[] cs=new char[32];
		char c=0;
		for(int i=uuid.length()/2,j=1;i-->0;){
			if((c=uuid.charAt(i))!='-'){
				cs[j++]=c;
			}
		}
		return String.valueOf(cs).trim();
	}

    /**
     * 获取带横线的长度为36的UUID串.
     * 
     * @return uuid.
     */
    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 验证一个字符串是否完全由纯数字组成的字符串，当字符串为空时也返回false.
     * 
     * @param str
     *            要判断的字符串 .
     * @return true or false .
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            return str.matches("\\d*");
        }
    }
}
