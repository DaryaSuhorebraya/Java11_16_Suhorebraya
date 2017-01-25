package com.dasha.analyzer.dao.impl;

import com.dasha.analyzer.dao.AnalyzerDAO;
import com.dasha.analyzer.dao.exception.DAOException;

import java.io.*;

/**
 * Created by Даша on 18.12.2016.
 */
public class AnalyzerDAOImpl implements AnalyzerDAO {
    private String filePath;

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public BufferedReader getInfoFilledBufferedReader() throws DAOException {
        BufferedReader bufferedReader;
        try {
            bufferedReader=new BufferedReader(new FileReader(new File(filePath)));
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        }
        return bufferedReader;
    }

}
