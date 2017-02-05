package com.dasha.parser.entity.bean;

import com.dasha.parser.entity.element.XMLElement;

import java.io.Serializable;

/**
 * Created by Даша on 23.01.2017.
 */
public class Listener extends XMLElement implements Serializable {
    private static final long serialVersionUID=1L;
    private String listenerClass;

    public Listener() {
    }

    public String getListenerClass() {
        return listenerClass;
    }

    public void setListenerClass(String listenerClass) {
        this.listenerClass = listenerClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Listener listener = (Listener) o;
        if (null==listenerClass){
            return listenerClass==listener.listenerClass;
        }
        else {
            if (!listenerClass.equals(listener.listenerClass)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return listenerClass != null ? listenerClass.hashCode() : 0;
    }

    @Override
    public void printElement() {
        System.out.println("listener: ");
        System.out.println("  listener-class: "+listenerClass);
        System.out.println("------------");
    }
}
