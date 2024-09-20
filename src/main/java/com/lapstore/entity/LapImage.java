package com.lapstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lap_images")
public class LapImage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lap_image_id")
	private int lapImageId;
	@ManyToOne
	@JoinColumn(name = "lap_id", nullable = false)
	private Lap lap;
	@Column(name = "image", columnDefinition = "text", nullable = false)
	private String image;

	public LapImage(int lapImageId, Lap lap, String image) {
		super();
		this.lapImageId = lapImageId;
		this.lap = lap;
		this.image = image;
	}

	public LapImage(Lap lap, String image) {
		super();
		this.lap = lap;
		this.image = image;
	}

	public LapImage() {
		super();
	}

	public int getLapImageId() {
		return lapImageId;
	}

	public void setLapImageId(int lapImageId) {
		this.lapImageId = lapImageId;
	}

	public Lap getLap() {
		return lap;
	}

	public void setLap(Lap lap) {
		this.lap = lap;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "LapImage [lap=" + lap + ", image=" + image + "]";
	}
}
