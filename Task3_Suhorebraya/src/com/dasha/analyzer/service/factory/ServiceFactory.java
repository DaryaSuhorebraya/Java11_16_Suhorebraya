package com.dasha.analyzer.service.factory;

import com.dasha.analyzer.service.AnalyzerService;
import com.dasha.analyzer.service.impl.AnalyzerServiceImpl;

/**
 * Created by Даша on 18.12.2016.
 */
public class ServiceFactory {
    private static final ServiceFactory instance=new ServiceFactory();
    private AnalyzerService analyzerService=new AnalyzerServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public AnalyzerService getAnalyzerService() {
        return analyzerService;
    }
}
