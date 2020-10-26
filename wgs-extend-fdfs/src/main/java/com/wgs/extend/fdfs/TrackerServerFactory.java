package com.wgs.extend.fdfs;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * TrackerServer 工厂类，创建对象池需要 BasePooledObjectFactory 对象或子类.
 * @author xiongxianze
 *
 */
public class TrackerServerFactory extends BasePooledObjectFactory<TrackerServer> {

	private TrackerClient trackerClient;

	public TrackerServerFactory(TrackerClient trackerClient) {
		this.trackerClient = trackerClient;
	}

	@Override
    public TrackerServer create() throws Exception {
		return trackerClient.getConnection();
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
