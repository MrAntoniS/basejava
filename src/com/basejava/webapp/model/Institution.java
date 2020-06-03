package com.basejava.webapp.model;

import java.util.List;

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
}
