package com.jfs.operations.lite.config;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.ResourceBundle;

@UtilityClass
public class SystemProperties {
	@Getter
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.LANGUAGE_RESOURCE_BUNDLE,
			new Locale(Constants.APPLICATION_LANGUAGE));
}
