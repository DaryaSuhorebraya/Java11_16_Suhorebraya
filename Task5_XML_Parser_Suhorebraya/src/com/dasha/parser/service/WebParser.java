package com.dasha.parser.service;

import com.dasha.parser.entity.bean.WebApp;
import com.dasha.parser.service.exception.ServiceException;

/**
 * Created by Даша on 05.02.2017.
 */
public interface WebParser {
    public WebApp parse(String filePath) throws ServiceException;
}
