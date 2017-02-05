package com.dasha.parser.entity.bean;

import com.dasha.parser.entity.element.XMLElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 05.02.2017.
 */
public class WebApp extends XMLElement implements Serializable {
    private static final long serialVersionUID=1L;
    private String id;
    private String version;
    private List<DisplayName> displayNameList;
    private List<WelcomeFileList> welcomeFileList;
    private List<Filter> filterList;
    private List<FilterMapping> filterMappingList;
    private List<Listener> listenerList;
    private List<Servlet> servletList;
    private List<ServletMapping> servletMappingList;
    private List<ErrorPage> errorPageList;

    public WebApp() {
        displayNameList=new ArrayList<>();
        welcomeFileList=new ArrayList<>();
        filterList=new ArrayList<>();
        filterMappingList=new ArrayList<>();
        listenerList=new ArrayList<>();
        servletList=new ArrayList<>();
        servletMappingList=new ArrayList<>();
        errorPageList=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<DisplayName> getDisplayNameList() {
        return displayNameList;
    }

    public void setDisplayNameList(List<DisplayName> displayNameList) {
        this.displayNameList = displayNameList;
    }

    public List<WelcomeFileList> getWelcomeFileList() {
        return welcomeFileList;
    }

    public void setWelcomeFileList(List<WelcomeFileList> welcomeFileList) {
        this.welcomeFileList = welcomeFileList;
    }

    public List<Filter> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public List<FilterMapping> getFilterMappingList() {
        return filterMappingList;
    }

    public void setFilterMappingList(List<FilterMapping> filterMappingList) {
        this.filterMappingList = filterMappingList;
    }

    public List<Listener> getListenerList() {
        return listenerList;
    }

    public void setListenerList(List<Listener> listenerList) {
        this.listenerList = listenerList;
    }

    public List<Servlet> getServletList() {
        return servletList;
    }

    public void setServletList(List<Servlet> servletList) {
        this.servletList = servletList;
    }

    public List<ServletMapping> getServletMappingList() {
        return servletMappingList;
    }

    public void setServletMappingList(List<ServletMapping> servletMappingList) {
        this.servletMappingList = servletMappingList;
    }

    public List<ErrorPage> getErrorPageList() {
        return errorPageList;
    }

    public void setErrorPageList(List<ErrorPage> errorPageList) {
        this.errorPageList = errorPageList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WebApp webApp = (WebApp) o;

        if (id != null ? !id.equals(webApp.id) : webApp.id != null) {
            return false;
        }
        if (version != null ? !version.equals(webApp.version) : webApp.version != null) {
            return false;
        }
        if (displayNameList != null ? !displayNameList.equals(webApp.displayNameList)
                : webApp.displayNameList != null) {
            return false;
        }
        if (welcomeFileList != null ? !welcomeFileList.equals(webApp.welcomeFileList)
                : webApp.welcomeFileList != null) {
            return false;
        }
        if (filterList != null ? !filterList.equals(webApp.filterList)
                : webApp.filterList != null) {
            return false;
        }
        if (filterMappingList != null ? !filterMappingList.equals(webApp.filterMappingList)
                : webApp.filterMappingList != null) {
            return false;
        }
        if (listenerList != null ? !listenerList.equals(webApp.listenerList)
                : webApp.listenerList != null) {
            return false;
        }
        if (servletList != null ? !servletList.equals(webApp.servletList)
                : webApp.servletList != null) {
            return false;
        }
        if (servletMappingList != null ? !servletMappingList.equals(webApp.servletMappingList)
                : webApp.servletMappingList != null) {
            return false;
        }
        return errorPageList != null ? errorPageList.equals(webApp.errorPageList) : webApp.errorPageList == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (displayNameList != null ? displayNameList.hashCode() : 0);
        result = 31 * result + (welcomeFileList != null ? welcomeFileList.hashCode() : 0);
        result = 31 * result + (filterList != null ? filterList.hashCode() : 0);
        result = 31 * result + (filterMappingList != null ? filterMappingList.hashCode() : 0);
        result = 31 * result + (listenerList != null ? listenerList.hashCode() : 0);
        result = 31 * result + (servletList != null ? servletList.hashCode() : 0);
        result = 31 * result + (servletMappingList != null ? servletMappingList.hashCode() : 0);
        result = 31 * result + (errorPageList != null ? errorPageList.hashCode() : 0);
        return result;
    }

    @Override
    public void printElement() {
        System.out.println("web-app: id= "+id+" version= "+version);
        if (displayNameList!=null){
            displayNameList.forEach(DisplayName::printElement);
        }
        if (welcomeFileList!=null){
            welcomeFileList.forEach(WelcomeFileList::printElement);
        }
        if (filterList!=null){
            filterList.forEach(Filter::printElement);
        }
        if (filterMappingList!=null){
            filterMappingList.forEach(FilterMapping::printElement);
        }
        if (listenerList!=null){
            listenerList.forEach(Listener::printElement);
        }
        if (servletList!=null){
            servletList.forEach(Servlet::printElement);
        }
        if (servletMappingList!=null){
            servletMappingList.forEach(ServletMapping::printElement);
        }
        if (errorPageList!=null){
            errorPageList.forEach(ErrorPage::printElement);
        }
        System.out.println("------------");
    }
}
