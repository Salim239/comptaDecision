package com.growup.comptadecision.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.growup.comptadecision.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.Region.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.Ville.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.SecteurActivite.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.Activite.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.ImpotMensuel.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.ImpotMensuelDetail.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.ImpotMensuel.class.getName() + ".impotMensuelDetails", jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.FicheClient.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.FicheClient.class.getName() + ".impotMensuelClients", jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.Cnss.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.ImpotMensuelClient.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.QuittanceMensuelleImpot.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.QuittanceMensuelleImpot.class.getName() + ".quittanceMensuelleImpotLines", jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.QuittanceMensuelleImpotLine.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.DeclarationAnnuelle.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.DeclarationEmployeurAnnuelle.class.getName(), jcacheConfiguration);
            cm.createCache(com.growup.comptadecision.domain.AcompteProvisionnel.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
