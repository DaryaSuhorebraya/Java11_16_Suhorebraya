package com.dasha.parser.entity.bean;

import com.dasha.parser.entity.element.XMLElement;

import java.io.Serializable;

/**
 * Created by Даша on 23.01.2017.
 */
public class FilterMapping extends XMLElement implements Serializable{
    private static final long serialVersionUID=1L;
    private String filterName;
    private String urlPattern;
    private String dispatcher;

    public FilterMapping() {
    }

    public String getFilterName() {

        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        FilterMapping filterMapping = (FilterMapping) o;

        if (null==filterName){
            return filterName==filterMapping.filterName;
        }
        else {
            if (!filterName.equals(filterMapping.filterName)){
                return false;
            }
        }
        if (null==urlPattern){
            return urlPattern==filterMapping.urlPattern;
        }
        else {
            if (!urlPattern.equals(filterMapping.urlPattern)){
                return false;
            }
        }
        if (null==dispatcher){
            return dispatcher==filterMapping.dispatcher;
        }
        else {
            if (!dispatcher.equals(filterMapping.dispatcher)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = filterName != null ? filterName.hashCode() : 0;
        result = 31 * result + (urlPattern != null ? urlPattern.hashCode() : 0);
        result = 31 * result + (dispatcher != null ? dispatcher.hashCode() : 0);
        return result;
    }

    @Override
    public void printElement() {
        System.out.println("filter-mapping: ");
        System.out.println("  filter-name: "+filterName);
        System.out.println("  url-pattern: "+urlPattern);
        System.out.println("  dispatcher: "+dispatcher);
        System.out.println("------------");
    }
}
