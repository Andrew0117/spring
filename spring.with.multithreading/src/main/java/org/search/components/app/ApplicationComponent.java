package org.search.components.app;

import org.search.entities.GoogleEntity;
import org.search.entities.YahooEntity;
import org.search.entities.base.BaseEntityI;
import org.search.entities.enumaration.SearchService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component(value = "applicationComponent")
@Scope(value = "singleton")
public class ApplicationComponent implements InitializingBean {

    @Value("${baseUrl.google}")
    private String baseUrlGoogle;

    @Value("${api_key.google}")
    private String apiKeyGoogle;

    @Value("${search_engine_id.google}")
    private String searchEngineIdGoogle;

    @Value("${baseUrl.yahoo}")
    private String baseUrlYahoo;

    private Map<SearchService, BaseEntityI> searchServiceMap;

    @Override
    public void afterPropertiesSet() throws Exception {

        this.searchServiceMap = new HashMap<>();
        this.searchServiceMap.put(SearchService.GOOGLE, new GoogleEntity(baseUrlGoogle, apiKeyGoogle, searchEngineIdGoogle));
        this.searchServiceMap.put(SearchService.YAHOO, new YahooEntity(baseUrlYahoo));

    }

    public Map<SearchService, BaseEntityI> getSearchServiceMap() {
        return searchServiceMap;
    }
}
