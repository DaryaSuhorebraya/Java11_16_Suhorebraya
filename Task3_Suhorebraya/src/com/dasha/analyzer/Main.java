package com.dasha.analyzer;

import com.dasha.analyzer.bean.Node;
import com.dasha.analyzer.service.AnalyzerService;
import com.dasha.analyzer.service.exception.ServiceException;
import com.dasha.analyzer.service.factory.ServiceFactory;

public class Main {

    public static void main(String[] args) {

        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        AnalyzerService analyzerService=serviceFactory.getAnalyzerService();
        try {
            //read consequentially

            analyzerService.setFilePath("src/com/dasha/analyzer/resource/notes");
           Node node;
            int i=1;
            while ((node=analyzerService.next(i))!=null){
                i++;
                System.out.println(node.getType()+" "+node.getContent());
            }


            //read all at once with regular expression
            analyzerService.nextWithRegEx();

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
