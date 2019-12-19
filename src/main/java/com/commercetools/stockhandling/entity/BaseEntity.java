/**
 * 
 */
package com.commercetools.stockhandling.entity;

import java.io.Serializable;

/**
 * @author M.Othman
 *
 */
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract String toString();

	public abstract boolean equals(Object obj);

	public abstract int hashCode();
}
