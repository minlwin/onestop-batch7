package com.jdc.payroll.configuration;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class ApiWebConfiguration implements WebMvcConfigurer{
	
	@Value("${spring.mvc.format.date}")
	private String dateFormat;
	@Value("${spring.mvc.format.date-time}")
	private String dateTimeFormat;

	@Bean
	Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return builder -> {
			builder.simpleDateFormat(dateFormat);
			builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
			builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
			builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
		};
	}
	
	@Bean
	DateTimeFormatter appDateFormatter() {
		return DateTimeFormatter.ofPattern(dateFormat);
	}
	
	@Bean
	DateTimeFormatter appDateTimeFormatter() {
		return DateTimeFormatter.ofPattern(dateTimeFormat);
	}	
}
