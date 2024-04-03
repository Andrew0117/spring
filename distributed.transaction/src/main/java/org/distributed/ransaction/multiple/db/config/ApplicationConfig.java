package org.distributed.ransaction.multiple.db.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Berezhnov Andrey on 2024-04-03 20:29
 * @see <a href="#">Andrey at andrew.my@yahoo.com</a>
 */
@Configuration
public class ApplicationConfig {

    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager ChainedTransactionManager(
            @Qualifier("mariadbPlatformTransactionManager") PlatformTransactionManager mariadbPlatformTransactionManager,
            @Qualifier("mssqlPlatformTransactionManager") PlatformTransactionManager mssqlPlatformTransactionManager
    ) {

        return new ChainedTransactionManager(mariadbPlatformTransactionManager, mssqlPlatformTransactionManager);
    }

}
