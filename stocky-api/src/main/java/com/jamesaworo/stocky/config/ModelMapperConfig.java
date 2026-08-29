package com.jamesaworo.stocky.config;

import com.jamesaworo.stocky.config.converter.LocalDateStringConverter;
import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ModelMapperConfig {

    Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
        @Override
        public LocalDate get() {
            return LocalDate.now();
        }
    };

    Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
        @Override
        protected LocalDate convert(String source) {
            return new LocalDateStringConverter().convert(source);
        }
    };


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.getConfiguration().setAmbiguityIgnored(true);

        mapper.createTypeMap(String.class, LocalDate.class);
        mapper.addConverter(toStringDate);
        mapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        return mapper;
    }
}
