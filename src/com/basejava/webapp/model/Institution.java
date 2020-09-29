package com.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Institution implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Institution EMPTY = new Institution("", "", Experience.EMPTY);

    private Link homePage;
    private List<Experience> experienceDescription;

    public Institution(String institutionName, String url, Experience... experienceDescription) {
        this(new Link(institutionName, url), Arrays.asList(experienceDescription));
    }

    public Institution(Link homePage, List<Experience> experienceDescription) {
        this.homePage = homePage;
        this.experienceDescription = experienceDescription;
    }

    public Institution() {
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Experience> getExperienceDescription() {
        return experienceDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institution that = (Institution) o;
        return homePage.equals(that.homePage) &&
                experienceDescription.equals(that.experienceDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, experienceDescription);
    }

    @Override
    public String toString() {
        return "Institution{" +
                "homePage=" + homePage +
                ", experienceDescription=" + experienceDescription +
                '}';
    }
}
