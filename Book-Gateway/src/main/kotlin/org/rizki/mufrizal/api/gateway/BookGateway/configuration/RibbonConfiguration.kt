package org.rizki.mufrizal.api.gateway.BookGateway.configuration

import com.netflix.client.config.DefaultClientConfigImpl
import com.netflix.client.config.IClientConfig
import com.netflix.loadbalancer.IPing
import com.netflix.loadbalancer.IRule
import com.netflix.loadbalancer.PingUrl
import com.netflix.loadbalancer.WeightedResponseTimeRule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy


/**
 * Created by rizkimufrizal on 5/28/17.
 */
@Configuration
class RibbonConfiguration {

    companion object {
        const val name = "SPRING-CLOUD-RIBBON"
    }

    @Bean
    fun ribbonPing(@Lazy config: IClientConfig): IPing {
        return PingUrl()
    }

    @Bean
    fun ribbonRule(@Lazy config: IClientConfig): IRule {
        return WeightedResponseTimeRule()
    }

    @Bean
    fun ribbonClientConfig(): IClientConfig {
        val config = DefaultClientConfigImpl()
        config.loadProperties(name)
        return config
    }

}