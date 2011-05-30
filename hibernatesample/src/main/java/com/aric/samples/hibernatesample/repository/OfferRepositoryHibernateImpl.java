/**
 * 
 */
package com.aric.samples.hibernatesample.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.aric.samples.hibernatesample.domain.Offer;

/**
 * @author Dursun KOC
 * 
 */
@Repository("offerRepositoryHibernate")
public class OfferRepositoryHibernateImpl extends HibernateDaoSupport implements
		OfferRepository {
	
	@Autowired
	public void init(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.samples.hibernatesample.repository.OfferRepository#saveOffer
	 * (com.aric.samples.hibernatesample.domain.Offer)
	 */
	@Override
	public Long saveOffer(Offer offer) {
		getHibernateTemplate().save(offer);
		return offer.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.samples.hibernatesample.repository.OfferRepository#updateOffer
	 * (com.aric.samples.hibernatesample.domain.Offer)
	 */
	@Override
	public Long updateOffer(Offer offer) {
		getHibernateTemplate().merge(offer);
		return offer.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.samples.hibernatesample.repository.OfferRepository#loadOffer
	 * (java.lang.Long)
	 */
	@Override
	public Offer loadOffer(Long id) {
		Offer load = getHibernateTemplate().load(Offer.class, id);
		return load;
	}

}
