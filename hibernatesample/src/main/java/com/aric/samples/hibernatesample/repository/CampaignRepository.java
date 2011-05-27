/**
 * 
 */
package com.aric.samples.hibernatesample.repository;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aric.samples.hibernatesample.domain.Campaign;

/**
 * @author Dursun KOC
 * 
 */
public interface CampaignRepository {

	/**
	 * @param campaign
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.NESTED)
	public Long saveCampaign(Campaign campaign);

	/**
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Campaign loadCampaign(Long id);
}
