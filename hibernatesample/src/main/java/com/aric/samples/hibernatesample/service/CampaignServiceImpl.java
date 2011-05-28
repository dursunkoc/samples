/**
 * 
 */
package com.aric.samples.hibernatesample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aric.samples.hibernatesample.domain.Campaign;
import com.aric.samples.hibernatesample.repository.CampaignRepository;

/**
 * @author Dursun KOC
 * 
 */
@Service
public class CampaignServiceImpl implements CampaignService {
	@Autowired
	@Qualifier("campaignRespositoryHibernate")
	private CampaignRepository repository;

	/**
	 * @param repository
	 */
	public void setRepository(CampaignRepository repository) {
		this.repository = repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.samples.hibernatesample.service.CampaignService#saveCampaign
	 * (com.aric.samples.hibernatesample.domain.Campaign)
	 */
	@Override
	public Long saveCampaign(Campaign campaign) {
		return this.repository.saveCampaign(campaign);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.samples.hibernatesample.service.CampaignService#findCampaign
	 * (java.lang.Long)
	 */
	@Override
	public Campaign findCampaign(Long id) {
		return this.repository.loadCampaign(id);
	}

}
