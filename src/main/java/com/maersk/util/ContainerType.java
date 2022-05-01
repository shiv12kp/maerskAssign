package com.maersk.util;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContainerType {

	DRY("DRY"), REEFER("REEFER"), EMPTY("");

	private String containerType;

	private ContainerType(String depCode) {

		this.containerType = depCode;
	}

	public String getContainerTypeEnum() {

		return this.containerType;
	}

	@JsonCreator
	public static ContainerType getContainerType(String value) {

		for (ContainerType containerTypeEnum : ContainerType.values()) {

			if (containerTypeEnum.getContainerTypeEnum().equals(value)) {

				return containerTypeEnum;
			}
		}

		return ContainerType.EMPTY;
	}

	@Override
	public String toString() {
		return containerType;
	}
}
