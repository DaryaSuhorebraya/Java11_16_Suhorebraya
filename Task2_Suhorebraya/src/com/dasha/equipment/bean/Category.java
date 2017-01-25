package com.dasha.equipment.bean;

import java.io.Serializable;

/**
 * Created by Даша on 16.12.2016.
 */
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;

    private int idCategory;
    private String title;


    public Category() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (null == obj){return false;}
        if (getClass() != obj.getClass()) { return false;}

        Category category = (Category) obj;

        if (idCategory != category.idCategory) {return false;}
        if (null==title) {return title==category.title;}
        else {
            if (!title.equals(category.title)){return false;}
        }
        return true;

    }

    @Override
    public int hashCode() {
        int result = idCategory;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
