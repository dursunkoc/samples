package com.aric.samples.hibernatesample.repository;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.Set;

import javax.sql.DataSource;

import junit.framework.Assert;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aric.samples.hibernatesample.domain.Campaign;
import com.aric.samples.hibernatesample.domain.Offer;

/**
 * @author Dursun KOC
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OfferRepositoryHibernateImplTest {

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
				null, false, false);
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
	@Qualifier("offerRepositoryHibernate")
	private OfferRepository repository;

	@Autowired
	@Qualifier("campaignRepositoryHibernate")
	private CampaignRepository campaignRepository;

	@Test
	@Rollback(true)
	public void testSaveOffer() {
		Offer offer = new Offer();
		offer.setEndDate(new Date());
		offer.setStartDate(new Date());
		offer.setName("my - offer");
		Campaign campaign = campaignRepository.loadCampaign(1L);
		offer.setCampaign(campaign);
		repository.saveOffer(offer);
	}

	@Test
	public void testUpdateOffer() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadOffer() {
		Campaign loadCampaign = campaignRepository.loadCampaign(1L);
		Set<Offer> offers = loadCampaign.getOffers();
		for (Offer offer : offers) {
			System.out.println(offer.getName());
			System.out.println(offer.getEndDate());
			System.out.println(offer.getStartDate());
			System.out.println(offer.getId());
			System.out.println("===================================");
		}
		Assert.assertTrue(offers.size() > 0);
	}

}
