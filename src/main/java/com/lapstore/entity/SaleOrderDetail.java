package com.lapstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(SaleOrderDetailPK.class)
@Table(name = "sale_order_details")
public class SaleOrderDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "sale_order_id")
	private SaleOrder saleOrder;
	@Id
	@ManyToOne
	@JoinColumn(name = "lap_id")
	private Lap lap;
	@Column(name = "order_qty", columnDefinition = "int", nullable = false)
	private int orderQty;
	@Column(name = "unit_price", columnDefinition = "money", nullable = false)
	private BigDecimal unitPrice;
	@Column(name = "line_total", columnDefinition = "money", nullable = false)
	private BigDecimal lineTotal;

	public SaleOrderDetail(SaleOrder saleOrder, Lap lap, int orderQty, BigDecimal unitPrice, BigDecimal lineTotal) {
		super();
		this.saleOrder = saleOrder;
		this.lap = lap;
		this.orderQty = orderQty;
		this.unitPrice = unitPrice;
		this.lineTotal = lineTotal;
	}

	public SaleOrderDetail() {
		super();
	}

	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	public Lap getLap() {
		return lap;
	}

	public void setLap(Lap lap) {
		this.lap = lap;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(BigDecimal lineTotal) {
		this.lineTotal = lineTotal;
	}

	@Override
	public String toString() {
		return "SaleOrderDetail [saleOrder=" + saleOrder + ", lap=" + lap + ", orderQty=" + orderQty + ", unitPrice="
				+ unitPrice + ", lineTotal=" + lineTotal + "]";
	}
}
