package com.dasha.parser.entity.bean;

import com.dasha.parser.entity.element.XMLElement;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Даша on 23.01.2017.
 */
public class Filter extends XMLElement implements Serializable{
    private static final long serialVersionUID=1L;
    private String filterName;
    private String filterClass;
    private List<InitParam> initParams;

    public Filter() {
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public List<InitParam> getInitParam() {
        return initParams;
    }

    public void setInitParam(List<InitParam> initParam) {
        this.initParams = initParam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Filter filter = (Filter) o;

        if (null==filterName){
            return filterName==filter.filterName;
        }
        else {
            if (!filterName.equals(filter.filterName)){
                return false;
            }
        }
        if (null==filterClass){
            return filterClass==filter.filterClass;
        }
        else {
            if (!filterClass.equals(filter.filterClass)){
                return false;
            }
        }
        return initParams != null ? initParams.equals(filter.initParams) : filter.initParams == null;

    }

    @Override
    public int hashCode() {
        int result = filterName != null ? filterName.hashCode() : 0;
        result = 31 * result + (filterClass != null ? filterClass.hashCode() : 0);
        result = 31 * result + (initParams != null ? initParams.hashCode() : 0);
        return result;
    }

    @Override
    public void printElement() {
        System.out.println("filter: ");
        System.out.println("  filter-name: "+filterName);
        System.out.println("  filter-class: "+filterClass);
        if (initParams!=null){
            initParams.forEach(InitParam::printElement);
        }
        System.out.println("------------");
    }
}
