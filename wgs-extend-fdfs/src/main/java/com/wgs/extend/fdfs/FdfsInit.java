<<<<<<< HEAD
<<<<<<< HEAD
package com.wgs.extend.fdfs;


import com.wgs.extend.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.boot.context.properties.ConfigurationProperties;
=======
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
package com.eaju.extend.fastdfs;


import com.eaju.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
<<<<<<< HEAD
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca

import java.io.IOException;

/**
 * 初始化ClientGlobal
 * @Description
 *
 * @Author  wanggsh
 * @Date    2019/6/15 21:05
 * @Version 1.0
 */
@Slf4j
<<<<<<< HEAD
<<<<<<< HEAD
@ConfigurationProperties(prefix = "wgs.extend.fdfs")
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
public class FdfsInit {

	private String charSet; //字符编码
	private String httpSecretKey; //密钥
	private String trackerServers; //tracker地址，多个用逗号隔开
	private Integer trackerHttpPort; //http服务端口
	private Boolean antiStealToken; //
	private Integer connectTimeout; //连接超时时间
	private Integer networkTimeout; //网络超时时间

    public void init(){
<<<<<<< HEAD
<<<<<<< HEAD
		if (!StringUtils.isEmpty(charSet)) {
			ClientGlobal.setG_charset(charSet);
		}
		if (!StringUtils.isEmpty(httpSecretKey)) {
			ClientGlobal.setG_secret_key(httpSecretKey);
		}
		if (!StringUtils.isEmpty(trackerServers) && trackerServers.trim().length() != 0) {
=======
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
		if (!StringUtil.isEmpty(charSet)) {
			ClientGlobal.setG_charset(charSet);
		}
		if (!StringUtil.isEmpty(httpSecretKey)) {
			ClientGlobal.setG_secret_key(httpSecretKey);
		}
		if (!StringUtil.isEmpty(trackerServers) && trackerServers.trim().length() != 0) {
<<<<<<< HEAD
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
			try {
				ClientGlobal.initByTrackers(trackerServers.trim());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (MyException e) {
				e.printStackTrace();
			}
		} else {
			throw new RuntimeException("trackerServers is required");
		}
		if (trackerHttpPort != null) {
			ClientGlobal.setG_tracker_http_port(trackerHttpPort);
		}
		if (antiStealToken != null) {
			ClientGlobal.setG_anti_steal_token(antiStealToken);
		}
		if (connectTimeout != null) {
			ClientGlobal.setG_connect_timeout(connectTimeout);
		}
		if (networkTimeout != null) {
			ClientGlobal.setG_network_timeout(networkTimeout);
		}
		log.info("ClientGlobal configInfo: {}", ClientGlobal.configInfo());
    }

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getHttpSecretKey() {
		return httpSecretKey;
	}

	public void setHttpSecretKey(String httpSecretKey) {
		this.httpSecretKey = httpSecretKey;
	}

	public String getTrackerServers() {
		return trackerServers;
	}

	public void setTrackerServers(String trackerServers) {
		this.trackerServers = trackerServers;
	}

	public Integer getTrackerHttpPort() {
		return trackerHttpPort;
	}

	public void setTrackerHttpPort(Integer trackerHttpPort) {
		this.trackerHttpPort = trackerHttpPort;
	}

	public boolean isAntiStealToken() {
		return antiStealToken;
	}

	public void setAntiStealToken(boolean antiStealToken) {
		this.antiStealToken = antiStealToken;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getNetworkTimeout() {
		return networkTimeout;
	}

	public void setNetworkTimeout(Integer networkTimeout) {
		this.networkTimeout = networkTimeout;
	}
}
