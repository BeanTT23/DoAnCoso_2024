package com.lapstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "laps")
public class Lap implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lap_id")
	private int lapId;
	@ManyToOne
	@JoinColumn(name = "lap_category_id", nullable = false)
	private LapCategory lapCategory;
	@Column(name = "color", columnDefinition = "nvarchar(80)", nullable = false)
	private String color;
	@Column(name = "price", columnDefinition = "money", nullable = false)
	private BigDecimal price;
	@Column(name = "quantity", columnDefinition = "int", nullable = false)
	private int quantity;

	@OneToMany(mappedBy = "lap")
	private List<SaleOrderDetail> listSaleOrderDetails;

	@OneToMany(mappedBy = "lap")
	private List<LapImage> listLapImages;

	@OneToMany(mappedBy = "lap")
	private List<LapReview> listLapReviews;

	public Lap(int lapId, LapCategory lapCategory, String color, BigDecimal price, int quantity) {
		super();
		this.lapId = lapId;
		this.lapCategory = lapCategory;
		this.color = color;
		this.price = price;
		this.quantity = quantity;
	}

	public Lap(LapCategory lapCategory, String color, BigDecimal price, int quantity) {
		super();
		this.lapCategory = lapCategory;
		this.color = color;
		this.price = price;
		this.quantity = quantity;
	}

	public Lap() {
		super();
	}

	public int getLapId() {
		return lapId;
	}

	public void setLapId(int lapId) {
		this.lapId = lapId;
	}

	public LapCategory getLapCategory() {
		return lapCategory;
	}

	public void setLapCategory(LapCategory lapCategory) {
		this.lapCategory = lapCategory;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "lap [lapId=" + lapId + ", lapCategory=" + lapCategory + ", color=" + color + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
}
