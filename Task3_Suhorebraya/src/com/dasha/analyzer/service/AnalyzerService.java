package com.dasha.analyzer.service;

import com.dasha.analyzer.bean.Node;
import com.dasha.analyzer.service.exception.ServiceException;

/**
 * Created by Даша on 18.12.2016.
 */
public interface AnalyzerService {

    void setFilePath(String filePath) throws ServiceException;
    Node getNode(int nodeNumber) throws ServiceException;
    Node next(int i) throws ServiceException;
    void nextWithRegEx() throws ServiceException;
}