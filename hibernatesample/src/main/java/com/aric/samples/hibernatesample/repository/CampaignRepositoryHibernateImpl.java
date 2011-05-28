/**
 * 
 */
package com.aric.samples.hibernatesample.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.aric.samples.hibernatesample.domain.Campaign;
import com.aric.samples.hibernatesample.exceptions.TooManyRecordsFound;

/**
 * @author Dursun KOC
 * 
 */
@Repository("campaignRespositoryHibernate")
public class CampaignRepositoryHibernateImpl extends HibernateDaoSupport implements
		CampaignRepository {

	@Autowired
	public void init(SessionFactory factory) {
		setSessionFactory(factory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.samples.hibernatesample.persistance.CampaignDao#saveCampaign
	 * (com.aric.samples.hibernatesample.domain.Campaign)
	 */
	@Override
	public Long saveCampaign(Campaign campaign) {
		getHibernateTemplate().save(campaign);
		return campaign.getId();
	}

	@Override
	public Campaign loadCampaign(Long id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
		criteria.add(Property.forName("id").eq(id));
		return (Campaign) exactOneRecord(criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	private Object exactOneRecord(DetachedCriteria criteria) {
		List<?> result = getHibernateTemplate().findByCriteria(criteria);
		if (result == null || result.size() == 0) {
			return null;
		} else if (result.size() == 1) {
			return result.get(0);
		} else {
			throw new TooManyRecordsFound("excepted 1 record got "
					+ result.size() + " for criteria:(" + criteria + ")");
		}
	}

}
