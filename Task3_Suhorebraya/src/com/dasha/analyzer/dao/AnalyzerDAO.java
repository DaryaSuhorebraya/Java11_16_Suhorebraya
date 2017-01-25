package com.dasha.analyzer.dao;

import com.dasha.analyzer.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.File;

/**
 * Created by Даша on 18.12.2016.
 */
public interface AnalyzerDAO {
    void setFilePath(String filePath) throws DAOException;
    BufferedReader getInfoFilledBufferedReader() throws DAOException;
}
