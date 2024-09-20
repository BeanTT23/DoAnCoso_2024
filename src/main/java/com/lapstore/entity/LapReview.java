package com.lapstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lap_reviews")
public class LapReview implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lap_review_id")
	private int lapReviewId;
	@ManyToOne
	@JoinColumn(name = "lap_id", nullable = false)
	private Lap lap;
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private User customer;
	@Column(name = "comment", columnDefinition = "nvarchar(500)", nullable = false)
	private String comment;
	@Column(name = "star", columnDefinition = "int", nullable = false)
	private int star;
	@Column(name = "review_date", columnDefinition = "datetime", nullable = false)
	private LocalDateTime reviewDate;

	public LapReview(int lapReviewId, Lap lap, User customer, String comment, int star, LocalDateTime reviewDate) {
		super();
		this.lapReviewId = lapReviewId;
		this.lap = lap;
		this.customer = customer;
		this.comment = comment;
		this.star = star;
		this.reviewDate = reviewDate;
	}

	public LapReview(Lap lap, User customer, String comment, int star, LocalDateTime reviewDate) {
		super();
		this.lap = lap;
		this.customer = customer;
		this.comment = comment;
		this.star = star;
		this.reviewDate = reviewDate;
	}

	public LapReview() {
		super();
	}

	public int getlapReviewId() {
		return lapReviewId;
	}

	public void setLapReviewId(int lapReviewId) {
		this.lapReviewId = lapReviewId;
	}

	public Lap getLap() {
		return lap;
	}

	public void setLap(Lap lap) {
		this.lap = lap;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public LocalDateTime getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Override
	public String toString() {
		return "LapReview [lapReviewId=" + lapReviewId + ", lap=" + lap + ", customer=" + customer + ", comment="
				+ comment + ", star=" + star + ", reviewDate=" + reviewDate + "]";
	}
}
