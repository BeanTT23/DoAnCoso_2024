package com.lapstore.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CartDetailPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cartHeader;
	private int lap;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lap;
		result = prime * result + cartHeader;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartDetailPK other = (CartDetailPK) obj;
		if (lap != other.lap)
			return false;
		if (cartHeader != other.cartHeader)
			return false;
		return true;
	}

}
