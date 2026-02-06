package com.jfs.operations.lite.infrastructure.aspect;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfs.operations.lite.config.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class AutoLoggableAspect {
	private ObjectMapper mapper;

	public AutoLoggableAspect() {
		mapper = new ObjectMapper();
		mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
				.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withSetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withCreatorVisibility(JsonAutoDetect.Visibility.NONE)
		);
	}

	@AfterReturning(value = "com.jfs.operations.lite.infrastructure.aspect.joinpoint.AutoLoggableJoinPoint.execPointcut()", returning = "result")
	public void methodInvoked(JoinPoint joinPoint, Object result) {
		Map data = Map.of("parameters", joinPoint.getArgs(), "result", result);
		try {
			log.info(MessageFormat.format(SystemProperties.getResourceBundle()
							.getString("aspects.logTemplate.autoLoggableAspect"), mapper.writeValueAsString(data),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()));
		} catch (JsonProcessingException e) {
			log.error("Data: " + data);
			log.error(e.getMessage(), e);
		}
	}

	@AfterThrowing(value = "com.jfs.operations.lite.infrastructure.aspect.joinpoint.AutoLoggableJoinPoint.execPointcut()", throwing = "e")
	public void methodRaisedException(JoinPoint joinPoint, Exception e) {
		Map data = Map.of("parameters", joinPoint.getArgs());
		try {
			log.error(MessageFormat.format(SystemProperties.getResourceBundle().getString("aspects.logTemplate.autoLoggableAspectException"),
					mapper.writeValueAsString(data),
					e.getMessage(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()));
		} catch (JsonProcessingException ex) {
			log.info("Data: " + data);
			log.error(e.getMessage(), e);
		}
	}
}
