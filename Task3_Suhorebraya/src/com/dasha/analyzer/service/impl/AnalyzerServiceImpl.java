package com.dasha.analyzer.service.impl;


import com.dasha.analyzer.bean.Node;
import com.dasha.analyzer.dao.AnalyzerDAO;
import com.dasha.analyzer.dao.exception.DAOException;
import com.dasha.analyzer.dao.factory.DAOFactory;
import com.dasha.analyzer.service.AnalyzerService;
import com.dasha.analyzer.service.exception.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Даша on 18.12.2016.
 */
public class AnalyzerServiceImpl implements AnalyzerService{

    @Override
    public void setFilePath(String filePath) throws ServiceException{
        if(filePath == null || filePath.isEmpty()){
            throw new ServiceException("Incorrect filePath");
        }
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            AnalyzerDAO analyzerDAO=daoFactory.getAnalyzerDAO();
            analyzerDAO.setFilePath(filePath);
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Node next(int i) throws ServiceException {
        Node node;
        while (!(node=getNode(i)).getType().equals("End")){
            i++;
            if (!node.getType().equals("Empty")){
                return node;
            }
        }
        return null;
    }
    // This method allows you to get a concrete node
    @Override
    public Node getNode(int nodeNumber) throws ServiceException {
        Node node;
        BufferedReader bufferedReader=null;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            AnalyzerDAO analyzerDAO=daoFactory.getAnalyzerDAO();
            bufferedReader=analyzerDAO.getInfoFilledBufferedReader();

            StringBuilder nodeString=readBuffer(bufferedReader,nodeNumber);
            node=new Node();
            if (nodeString.toString().equals("End of a document")){
                node.setType("End");
            } else {
            setNodeCharacteristics(node,nodeString);
            }
            if (node.getType()==null){
                node.setType("Empty");
            }
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
        finally {
            try {
                if (bufferedReader!=null){
                bufferedReader.close();
                }
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        }
        return node;
    }
    private StringBuilder readBuffer(BufferedReader bufferedReader, int nodeNumber)
            throws ServiceException{
        StringBuilder nodeString;
        if (nodeNumber % 2 != 0) {
            nodeString=getNodeInfoForTag(bufferedReader,nodeNumber);
        } else {
            nodeString=getNodeInfoForElementContent(bufferedReader,nodeNumber);
        }
        return nodeString;
    }
    private void checkFirstCharacter(char firstCharacter)
            throws ServiceException {
        if (firstCharacter!='<') {
            throw new ServiceException("Invalid xml");
        }
    }

    private int countCharacter(int startQuantityOfTag,char character,char comparedCharacter){
        int quantityOfTag=startQuantityOfTag;
        if ( character == comparedCharacter){
            quantityOfTag++;
        }
        return quantityOfTag;
    }
    private StringBuilder getNodeInfoForTag(BufferedReader bufferedReader,int nodeNumber)
            throws ServiceException{
        StringBuilder nodeString=new StringBuilder();
        int quantityOfTag=1;
        int checkedCharacter;
        try {
            checkFirstCharacter((char) bufferedReader.read());
            while ((checkedCharacter = bufferedReader.read()) != -1) {
                if (((char) checkedCharacter != '\r') && ((char) checkedCharacter != '\n')) {
                    quantityOfTag = countCharacter(quantityOfTag, (char) checkedCharacter, '<');
                    if (2 * quantityOfTag - 1 == nodeNumber) {
                        if ((char) checkedCharacter != '>')
                            nodeString.append((char) checkedCharacter);
                        else {
                            nodeString.append('>');
                            break;
                        }
                    }
                }
            }
            if (checkedCharacter == -1){
                nodeString.append("End of a document");
            }
        } catch (IOException e){
            throw new ServiceException(e);
        }
        return nodeString;
    }
    private StringBuilder getNodeInfoForElementContent(BufferedReader bufferedReader,int nodeNumber)
            throws ServiceException{
        StringBuilder nodeString=new StringBuilder();
        int quantityOfTag=0;
        int checkedCharacter;
        try {
            checkFirstCharacter((char) bufferedReader.read());
            while ((checkedCharacter = bufferedReader.read()) != -1) {
                if (((char) checkedCharacter!= '\r') && ((char) checkedCharacter != '\n')) {
                    quantityOfTag = countCharacter(quantityOfTag, (char) checkedCharacter, '>');
                    if (2 * quantityOfTag == nodeNumber) {
                        if ((char) checkedCharacter != '<') {
                            if (((char) checkedCharacter != '>'))
                                nodeString.append((char) checkedCharacter);
                        } else {
                            break;
                        }
                    }
                }
            }
            if (checkedCharacter == -1){
                nodeString.append("End of a document");
            }
        } catch (IOException e){
            throw new ServiceException(e);
        }
        return nodeString;
    }

    private void setNodeCharacteristics(Node node, StringBuilder nodeString){
        if (!nodeString.toString().equals("")) {
            if (nodeString.charAt(0) == '<') {
                if (nodeString.charAt(1) == '/') {
                    node.setType("Close Tag");
                    node.setContent(nodeString.toString());
                } else {
                    node.setType("Open tag");
                    node.setContent(nodeString.toString());
                }
            } else {
                if (!isWhiteSpaceContent(nodeString)) {
                    node.setType("Content of element");
                    node.setContent(nodeString.toString());
                }
            }
        }
    }
    private boolean isWhiteSpaceContent(StringBuilder nodeString){
        int whiteSpaceQuantity=0;
        for (int i=0; i<nodeString.length();i++){
            if (Character.isWhitespace(nodeString.charAt(i))){
                whiteSpaceQuantity++;
            }
        }
        return (whiteSpaceQuantity==nodeString.length());
    }



    //First experience in Regular Expression. This method is suitable to
    // formatted xml(read lines) and to not so large files(read all at once).
    @Override
    public void nextWithRegEx() throws ServiceException {
        BufferedReader bufferedReader=null;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            AnalyzerDAO analyzerDAO=daoFactory.getAnalyzerDAO();
            bufferedReader=analyzerDAO.getInfoFilledBufferedReader();
            List<Node> nodeList=fillNodeList(bufferedReader);
            for (Node node:nodeList){
                System.out.println(node.getType()+" "+node.getContent());
            }
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
        finally {
            try {
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        }
    }




    private List<Node> fillNodeList(BufferedReader bufferedReader)
            throws ServiceException {
        Node node;
        List<Node> nodeList=new ArrayList<>();
        String findNodeRegEx="(<[A-Za-z0-9]+?\\s*[A-Za-z]*\\s*=*\\s*\"*[A-Za-z0-9]*\"*>)"+
                "|(</[a-zA-Z]+>)|(<[a-zA-Z]+/>)|([^ \"][А-Яа-я ]+)";
        Pattern findNodePattern = Pattern.compile(findNodeRegEx);
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher m = findNodePattern.matcher(line);
                while (m.find()) {
                    String nodeString=m.group();
                    if (nodeString!=null&&!nodeString.isEmpty()){
                        node=new Node();
                        StringBuilder nodeStringBuilder=new StringBuilder(nodeString);
                        setNodeCharacteristics(node,nodeStringBuilder);
                        nodeList.add(node);
                    }
                }
            }
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return nodeList;
    }


}
