/**
 * 
 */
package com.aric.samples.hibernatesample.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aric.samples.hibernatesample.domain.Campaign;

/**
 * @author Dursun KOC
 * 
 */
public interface CampaignService {
	/**
	 * @param campaign
	 */
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public Long saveCampaign(Campaign campaign);

	/**
	 * @param id
	 * @return
	 */
	public Campaign findCampaign(Long id);

}
