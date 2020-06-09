package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class Institution {
    private final Link homePage;
    private final List<ExperienceInTheInstitution> experienceDescription;

    public Institution(String institutionName, String url, List<ExperienceInTheInstitution> experienceDescription) {
        this.homePage = new Link(institutionName, url);
        this.experienceDescription = experienceDescription;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<ExperienceInTheInstitution> getExperienceDescription() {
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
}
