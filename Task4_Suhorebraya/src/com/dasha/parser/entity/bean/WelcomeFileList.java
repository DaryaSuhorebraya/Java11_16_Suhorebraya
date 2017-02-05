package com.dasha.parser.entity.bean;

import com.dasha.parser.entity.element.XMLElement;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Даша on 23.01.2017.
 */
public class WelcomeFileList extends XMLElement implements Serializable{
    private static final long serialVersionUID=1L;
    private List<String> welcomeFile;

    public WelcomeFileList() {
    }

    public List<String> getWelcomeFile() {
        return welcomeFile;
    }

    public void setWelcomeFile(List<String> welcomeFile) {
        this.welcomeFile = welcomeFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        WelcomeFileList welcomeFileList = (WelcomeFileList) o;

        return welcomeFile != null
                ? welcomeFile.equals(welcomeFileList.welcomeFile)
                : welcomeFileList.welcomeFile == null;

    }

    @Override
    public int hashCode() {
        return welcomeFile != null ? welcomeFile.hashCode() : 0;
    }

    @Override
    public void printElement() {
        System.out.println("welcome-file-list: ");
        if (welcomeFile!=null){
            for (String welcFile: welcomeFile){
                System.out.println("  welcome-file: "+welcFile);
            }
        }
        System.out.println("------------");
    }
}
