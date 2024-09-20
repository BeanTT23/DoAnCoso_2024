package com.lapstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(CartDetailPK.class)
@Table(name = "cart_details")
public class CartDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "cart_header_id")
	private CartHeader cartHeader;
	@Id
	@ManyToOne
	@JoinColumn(name = "lap_id")
	private Lap lap;
	@Column(name = "lap_qty", columnDefinition = "int", nullable = false)
	private int lapQty;

	public CartDetail(CartHeader cartHeader, Lap lap, int lapQty) {
		super();
		this.cartHeader = cartHeader;
		this.lap = lap;
		this.lapQty = lapQty;
	}

	public CartDetail() {
		super();
	}

	public CartHeader getCartHeader() {
		return cartHeader;
	}

	public void setCartHeader(CartHeader cartHeader) {
		this.cartHeader = cartHeader;
	}

	public Lap getLap() {
		return lap;
	}

	public void setLap(Lap lap) {
		this.lap = lap;
	}

	public int getLapQty() {
		return lapQty;
	}

	public void setLapQty(int lapQty) {
		this.lapQty = lapQty;
	}

	@Override
	public String toString() {
		return "CartDetail [cartHeader=" + cartHeader + ", lap=" + lap + ", lapQty=" + lapQty + "]";
	}
}
