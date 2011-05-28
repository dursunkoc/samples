/**
 * 
 */
package com.aric.samples.hibernatesample.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aric.samples.hibernatesample.domain.Campaign;

/**
 * @author Dursun KOC
 *
 */
@Repository
public class CampaignRepositoryJDBCImpl extends JdbcTemplate implements CampaignRepository {

	/* (non-Javadoc)
	 * @see com.aric.samples.hibernatesample.repository.CampaignRepository#saveCampaign(com.aric.samples.hibernatesample.domain.Campaign)
	 */
	@Override
	public Long saveCampaign(Campaign campaign) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.aric.samples.hibernatesample.repository.CampaignRepository#loadCampaign(java.lang.Long)
	 */
	@Override
	public Campaign loadCampaign(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
