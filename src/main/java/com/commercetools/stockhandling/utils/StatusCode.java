/**
 * 
 */
package com.commercetools.stockhandling.utils;

/**
 * @author M.Othman
 *
 */
public enum StatusCode {

	SUCCESSFULL(201), OUTDATEDSTOCK(204), BADREQUEST(407), NOTFOUND(408), UNIQUE(409), DATENOTVALID(410);

	private final int value;

	StatusCode(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
