<<<<<<< HEAD
<<<<<<< HEAD
package com.wgs.extend.fdfs;
=======
package com.eaju.extend.fastdfs;
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
package com.eaju.extend.fastdfs;
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca

/**
 * TrackerServer 工厂类，创建对象池需要 BasePooledObjectFactory 对象或子类.
 * @author xiongxianze
 *
 */
public class TrackerServerFactory extends BasePooledObjectFactory<TrackerServer> {

<<<<<<< HEAD
<<<<<<< HEAD
	private TrackerClient trackerClient;

	public TrackerServerFactory(TrackerClient trackerClient) {
		this.trackerClient = trackerClient;
	}

	@Override
    public TrackerServer create() throws Exception {
		return trackerClient.getConnection();
=======
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
	@Autowired
	private TrackerClient trackerClient;

	@Override
    public TrackerServer create() throws Exception {
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
<<<<<<< HEAD
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
=======
>>>>>>> a58f1eb481476ee6ee14e24d91025442b0a10fca
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
