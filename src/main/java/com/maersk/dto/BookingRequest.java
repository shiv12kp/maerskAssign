package com.maersk.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.maersk.util.TimestampValidate;

@Table
@Entity
public class BookingRequest extends CheckAvaiableRequest {

	@Id
	@GeneratedValue
	Integer UUID;

	@Column
	@TimestampValidate
	@NotNull
	@NotEmpty
	String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getUUID() {
		return UUID;
	}

	public void setUUID(Integer uUID) {
		UUID = uUID;
	}
	
	@Override
	public String toString() {
		return "BookingRequest [containerSize=" + containerSize + ", containerType=" + containerType
				+ ", origin=" + origin + ", destination=" + destination + ", quantity=" + quantity +
				", timestamp="+timestamp+ "]";
	}

}
