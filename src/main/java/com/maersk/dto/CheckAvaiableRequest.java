package com.maersk.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.maersk.util.ContainerType;
import com.maersk.util.ContainersizeValidate;
import com.maersk.util.EnumNamePattern;

@MappedSuperclass
public class CheckAvaiableRequest {
	
	@Column
	@ContainersizeValidate(acceptedValues= {20,40},message = "Container size should 20 or 40")
	protected Integer containerSize; // – either 20 or 40
	
	@Column
	@Enumerated(EnumType.STRING)
	@EnumNamePattern(acceptedValues={"DRY", "REEFER"}, message = "Container type should be DRY or REEFER")
	protected ContainerType containerType; // – DRY, REEFER

	@Column
	@Size(min = 5, max = 20, message = "Origin string length must be between 5 and 20.")
	protected String origin; // – min 5, max 20

	@Column
	@Size(min = 5, max = 20, message = "Destination string length must be between 5 and 20.")
	protected String destination; // – min 5, max 20

	@Column
	@Min(value = 1, message = "Quantity should be greater than 1")
	@Max(value = 100, message = "Quantity should be less than 100")
	protected Integer quantity; // – min 1, max 100

	public Integer getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(Integer containerSize) {
		this.containerSize = containerSize;
	}

	public ContainerType getContainerType() {
		return containerType;
	}

	public void setContainerType(ContainerType containerTypeEnum) {
		this.containerType = containerTypeEnum;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CheckAvaiableRequest [containerSize=" + containerSize + ", containerType=" + containerType + ", origin="
				+ origin + ", destination=" + destination + ", quantity=" + quantity + "]";
	}
	
	
}
