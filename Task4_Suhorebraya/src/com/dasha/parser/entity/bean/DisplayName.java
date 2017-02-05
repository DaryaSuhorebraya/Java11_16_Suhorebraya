package com.dasha.parser.entity.bean;

import com.dasha.parser.entity.element.XMLElement;

import java.io.Serializable;

/**
 * Created by Даша on 23.01.2017.
 */
public class DisplayName extends XMLElement implements Serializable{
    private static final long serialVersionUID=1L;
    private String description;

    public DisplayName() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        DisplayName displayName = (DisplayName) o;
        if (null==description) {
            return description==displayName.description;
        }
        else {
            if (!description.equals(displayName.description)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }

    @Override
    public void printElement() {
        System.out.println("display-name: "+description);
        System.out.println("------------");
    }
}
