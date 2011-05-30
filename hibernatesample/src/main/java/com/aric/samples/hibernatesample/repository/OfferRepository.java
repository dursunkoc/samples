/**
 * 
 */
package com.aric.samples.hibernatesample.repository;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aric.samples.hibernatesample.domain.Offer;

/**
 * @author Dursun KOC
 *
 */
public interface OfferRepository {
	/**
	 * @param campaign
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.NESTED)
	public Long saveOffer(Offer offer);
	/**
	 * @param campaign
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.NESTED)
	public Long updateOffer(Offer offer);

	/**
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Offer loadOffer(Long id);
}
