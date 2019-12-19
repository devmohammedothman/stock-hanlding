/**
 * 
 */
package com.commercetools.stockhandling.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author M.Othman
 *
 */
@Entity
@Table(name = "stock")
public class Stock extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This ID is integer auto increment primary key Used instead of String as
	 * primary key to not cause any performance issue
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "auto_id")
	private Long id;
	
	/**
	 * The stock id is unique key which identifies the stock and visible to
	 * consumers
	 */
	@Column(name = "stock_id", nullable = false, unique = true)
	private String stockId;
	
	/**
	 * The product id is unique key which identifies the product and visible to consumers
	 * Represent relation between Stock and Product which is One to One mapping
	 * Stock object is the owner of product 
	 */
	@OneToOne(cascade = CascadeType.PERSIST, fetch =  FetchType.LAZY)
	@JoinColumn(name="product_id",nullable = false , unique = true , referencedColumnName="product_id", columnDefinition="VARCHAR(45)")
	private Product product;
	
	/**
	 * Quantity represents available quantity of specific product
	 * */
	@Column(name = "available_quantity" , nullable = false)
	private int quantity;
	
	/**
	 * Sold Quantity represents sold quantity of specific product
	 * */
	@Column(name = "sold_quantity", nullable = true)
	private int soldQuantity;
	
	/**
	 * Stock update date/time
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "update_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@Column(name = "version")
	@Version
	@NotNull
	private Long version;

	/**
	 * Default Constructor can used as a quick way to initialize product object
	 */
	public Stock() {}

	/**
	 * Parameterized constructor
	 * @param stockId
	 * @param quantity
	 * @param soldQuantity
	 */
	public Stock(String stockId, int quantity, int soldQuantity, Date updateDate) {
		this.stockId = stockId;
		this.quantity = quantity;
		this.soldQuantity = soldQuantity;
		this.updateDate = updateDate;
	}
	

	/**
	 * @param stockId
	 * @param quantity
	 * @param soldQuantity
	 * @param updateDate
	 * @param product
	 */
	public Stock(String stockId, int quantity, int soldQuantity, Date updateDate,Product product) {
		super();
		this.stockId = stockId;
		this.product = product;
		this.quantity = quantity;
		this.soldQuantity = soldQuantity;
		this.updateDate = updateDate;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the stockId
	 */
	public String getStockId() {
		return stockId;
	}

	/**
	 * @param stockId the stockId to set
	 */
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the soldQuantity
	 */
	public int getSoldQuantity() {
		return soldQuantity;
	}

	/**
	 * @param soldQuantity the soldQuantity to set
	 */
	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", quantity=" + quantity + ", soldQuantity=" + soldQuantity
				+ ", updateDate=" + updateDate + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((stockId == null) ? 0 : stockId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stockId == null) {
			if (other.stockId != null)
				return false;
		} else if (!stockId.equals(other.stockId))
			return false;
		return true;
	}
	
	
	
}
