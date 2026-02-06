package com.jfs.operations.lite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.jdbc.DataSourcePoolMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.jfs.operations.lite", exclude = { DataSourceAutoConfiguration.class, DataSourcePoolMetricsAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JfsOperationsLiteApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(JfsOperationsLiteApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JfsOperationsLiteApplication.class);
	}
}
