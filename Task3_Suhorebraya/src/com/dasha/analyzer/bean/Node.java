package com.dasha.analyzer.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 18.12.2016.
 */
public class Node implements Serializable{
    private static final long serialVersionUID = 1L;
    private String type;
    private String content;

    public Node() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) {
            return true;
        }
        if (null==obj) {
            return false;
        }
        if (getClass()!=obj.getClass()){
            return false;
        }

        Node node=(Node) obj;
        if (null==type) {
            return type==node.type;
        }
        else {
            if (!type.equals(node.type)) {
                return false;
            }
        }

        if (null==content){
            return content==node.content;
        }
        else {
            if (!content.equals(node.content)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
