package com.jfs.operations.lite.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistry {
	public FilterRegistrationBean<SessionLocaleFilter> filter() {
		FilterRegistrationBean<SessionLocaleFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new SessionLocaleFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}
