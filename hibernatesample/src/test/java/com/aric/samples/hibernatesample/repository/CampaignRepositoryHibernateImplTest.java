/**
 * 
 */
package com.aric.samples.hibernatesample.repository;

import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aric.samples.hibernatesample.domain.Campaign;

/**
 * @author Dursun KOC
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CampaignRepositoryHibernateImplTest {
	public static DataSource dataSource = setupDataSource();

	private static DataSource setupDataSource() {
		String connectURI = "jdbc:mysql://localhost:3306/tomcat";
		String user = "tomcat";
		String password = "42414241";
		String driverName = "com.mysql.jdbc.Driver";
		int maxActive = 100;
		int maxIdle = 30;
		int maxWait = 10000;
		int minIdle = 1;
		Config config = new Config();
		try {
			Class.forName(driverName);
			config.maxActive = maxActive;
			config.maxIdle = maxIdle;
			config.maxWait = maxWait;
			config.minIdle = minIdle;
			config.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		ObjectPool connectionPool = new GenericObjectPool(null, config);
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
				connectURI, user, password);
		new PoolableConnectionFactory(connectionFactory, connectionPool, null,
				null, false, true);
		PoolingDataSource ds = new PoolingDataSource(connectionPool);
		final SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		builder.bind("java:comp/env/jdbc/mysqlTomcatDB", ds);
		try {
			builder.activate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SimpleNamingContextBuilder.emptyActivatedContextBuilder();
	}

	@Autowired
	CampaignRepository repository;

	/**
	 * Test method for
	 * {@link com.aric.samples.hibernatesample.repository.CampaignRepositoryHibernateImpl#saveCampaign(com.aric.samples.hibernatesample.domain.Campaign)}
	 * .
	 */
	@Test
	public void testSaveCampaign() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			Campaign campaign = new Campaign();
			campaign.setEndDate(new Date());
			campaign.setStartDate(new Date());
			campaign.setName("campaign-" + i);
			repository.saveCampaign(campaign);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}

	/**
	 * Test method for
	 * {@link com.aric.samples.hibernatesample.repository.CampaignRepositoryHibernateImpl#loadCampaign(java.lang.Long)}
	 * .
	 */
	@Test
	public void testLoadCampaign() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			long l = i + 1L;
			repository.loadCampaign(l);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}

}
