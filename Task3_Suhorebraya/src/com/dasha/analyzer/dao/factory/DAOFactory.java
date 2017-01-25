package com.dasha.analyzer.dao.factory;

import com.dasha.analyzer.dao.AnalyzerDAO;
import com.dasha.analyzer.dao.impl.AnalyzerDAOImpl;

/**
 * Created by Даша on 18.12.2016.
 */
public class DAOFactory {
    private static final DAOFactory instance=new DAOFactory();
    private AnalyzerDAO analyzerDAO=new AnalyzerDAOImpl();
    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public AnalyzerDAO getAnalyzerDAO() {
        return analyzerDAO;
    }
}
