package com.panopticode.openapi.configuration;

import com.panopticode.openapi.model.Difficulty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

/**
 * This class provides Spring Converter beans for the enum models in the OpenAPI specification.
 *
 * By default, Spring only converts primitive types to enums using Enum::valueOf, which can prevent
 * correct conversion if the OpenAPI specification is using an `enumPropertyNaming` other than
 * `original` or the specification has an integer enum.
 */
@Configuration(value = "com.panopticode.openapi.configuration.enumConverterConfiguration")
public class EnumConverterConfiguration {

    @Bean(name = "com.panopticode.openapi.configuration.EnumConverterConfiguration.difficultyConverter")
    Converter<String, Difficulty> difficultyConverter() {
        return new Converter<String, Difficulty>() {
            @Override
            public Difficulty convert(String source) {
                return Difficulty.fromValue(source);
            }
        };
    }

}
