/**
 * 
 */
package com.aric.samples.hibernatesample.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.aric.samples.hibernatesample.domain.Campaign;

/**
 * @author Dursun KOC
 * 
 */
@WebService
public interface CampaignWebService {
	/**
	 * @param myPool
	 * @return
	 */
	@WebMethod(operationName = "createCampaign")
	@WebResult(name = "campaignId")
	public Long saveCampign(@WebParam(name = "campaign") Campaign campaign);

	/**
	 * @param id
	 * @return
	 */
	@WebMethod(operationName = "findCampaign")
	@WebResult(name = "campaign")
	public Campaign loadCampaign(@WebParam(name = "campaignId") Long id);

}
