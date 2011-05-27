/**
 * 
 */
package com.aric.samples.hibernatesample.exceptions;

/**
 * @author Dursun KOC
 * 
 */
public class TooManyRecordsFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5641133378118066251L;

	/**
	 * @param message
	 */
	public TooManyRecordsFound(String message) {
		super(message);
	}

}
