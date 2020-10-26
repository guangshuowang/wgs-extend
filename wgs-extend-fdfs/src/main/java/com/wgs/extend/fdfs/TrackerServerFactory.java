package com.eaju.extend.fastdfs;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TrackerServer 工厂类，创建对象池需要 BasePooledObjectFactory 对象或子类.
 * @author xiongxianze
 *
 */
public class TrackerServerFactory extends BasePooledObjectFactory<TrackerServer> {

	@Autowired
	private TrackerClient trackerClient;

	@Override
    public TrackerServer create() throws Exception {
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    @Override
    public PooledObject<TrackerServer> wrap(TrackerServer trackerServer) {
        return new DefaultPooledObject<>(trackerServer);
    }

	@Override
	public void destroyObject(PooledObject<TrackerServer> p) throws Exception {
		p.getObject().close();
	}
}
