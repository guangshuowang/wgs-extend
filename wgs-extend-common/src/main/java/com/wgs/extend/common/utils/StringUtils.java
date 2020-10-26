<<<<<<< HEAD
package com.wgs.extend.common.utils;


=======
package com.eaju.common.utils;


import org.springframework.util.StringUtils;

>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
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

<<<<<<< HEAD
public final class StringUtils {
=======
public final class StringUtil {

    /**
     * 私有构造方法,将该工具类设为单例模式.
     */
    private StringUtil() {
    }
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca

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
<<<<<<< HEAD
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim()) || "null".equals(str) || "undefined".equals(str);
    }

=======
     * 函数功能说明 ： 判断字符串是否为空 . 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param str
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 函数功能说明 ： 判断对象数组是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Object[] obj) {
        return null == obj || 0 == obj.length;
    }

    /**
     * 函数功能说明 ： 判断对象是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        return !(obj instanceof Number) ? false : false;
    }

    /**
     * 函数功能说明 ： 判断集合是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(List<?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 函数功能说明 ： 判断Map集合是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 函数功能说明 ： 获得文件名的后缀名. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param fileName
     * @参数： @return
     * @return String
     * @throws
     */
    public static String getExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca

    /**
     * 获取去掉横线的长度为32的UUID串.
     * 
     * @return uuid.
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

	public static String get16UUID() {
<<<<<<< HEAD
=======
//		return get32UUID().substring(0, 16);
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
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
<<<<<<< HEAD
        if (isEmpty(str)) {
=======
        if (StringUtils.isEmpty(str)) {
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
            return false;
        } else {
            return str.matches("\\d*");
        }
    }
<<<<<<< HEAD
=======

    /**
     * 计算采用utf-8编码方式时字符串所占字节数
     * 
     * @param content
     * @return
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
			// 汉字采用utf-8编码时占3个字节
			size = content.getBytes(StandardCharsets.UTF_8).length;
		}
        return size;
    }

    /**
     * 函数功能说明 ： 截取字符串拼接in查询参数. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param ids
     * @参数： @return
     * @return String
     * @throws
     */
    public static List<String> getInParam(String param) {
        boolean flag = param.contains(",");
        List<String> list = new ArrayList<String>();
        if (flag) {
            list = Arrays.asList(param.split(","));
        } else {
            list.add(param);
        }
        return list;
    }

>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
}
