package com.dasha.analyzer.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 18.12.2016.
 */
public class Node {
    //private String name;
    private String type;
    private String content;
   // private boolean hasAttributes;

/*    public void setAttributes(List<Attribute> attributes) {
        this.attributes= attributes;
    }*/

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

}
