/**
 * 
 */
package com.aric.samples.hibernatesample.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.aric.samples.hibernatesample.domain.Campaign;

/**
 * @author Dursun KOC
 * 
 */
@Repository("campaignRespositoryJdbc")
public class CampaignRepositoryJDBCImpl extends NamedParameterJdbcDaoSupport
		implements CampaignRepository {

	@Autowired
	protected void init(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.samples.hibernatesample.repository.CampaignRepository#saveCampaign
	 * (com.aric.samples.hibernatesample.domain.Campaign)
	 */
	@Override
	public Long saveCampaign(Campaign campaign) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(
				campaign);
		KeyHolder holder = new GeneratedKeyHolder();
		this.getNamedParameterJdbcTemplate()
				.update("insert into campaign(id,name,startDate,endDate) values(?,?,?,?)",
						parameterSource, holder);
		return holder.getKey().longValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.samples.hibernatesample.repository.CampaignRepository#loadCampaign
	 * (java.lang.Long)
	 */
	@Override
	public Campaign loadCampaign(Long id) {
		List<Campaign> result = this.getJdbcTemplate().query(
				"select id,name,startdate,enddate from campaign where id=?",
				new Object[] { id }, new RowMapper<Campaign>() {
					@Override
					public Campaign mapRow(ResultSet rs, int rownum)
							throws SQLException {
						return null;
					}
				});
		return result.get(0);
	}

}
