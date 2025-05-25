package com.nexos.api_user.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        BaseConfig.class,
        LocaleConfiguration.class
})
public class AppConfig {
}
