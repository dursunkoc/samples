/**
 * 
 */
package com.aric.samples.hibernatesample.webservice;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aric.samples.hibernatesample.domain.Campaign;
import com.aric.samples.hibernatesample.service.CampaignService;

/**
 * @author Dursun KOC
 * 
 */
@WebService
@Service("campaignWebService")
public class CampaignWebServiceImpl implements CampaignWebService {
	@Autowired
	private CampaignService campaignService;

	/**
	 * @param campaignService
	 */
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.samples.hibernatesample.webservice.CampaignWebService#saveCampign
	 * (com.aric.samples.hibernatesample.domain.Campaign)
	 */
	@Override
	public Long saveCampign(Campaign campaign) {
		return campaignService.saveCampaign(campaign);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.samples.hibernatesample.webservice.CampaignWebService#loadCampaign
	 * (java.lang.Long)
	 */
	@Override
	public Campaign loadCampaign(Long id) {
		return campaignService.findCampaign(id);
	}

}
