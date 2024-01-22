package com.mcommandes.microservicecommandes.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs-ms")
@RefreshScope
@Getter
@Setter
public class ApplicationPropertiesConfiguration {

    private int commandesLast;


}
