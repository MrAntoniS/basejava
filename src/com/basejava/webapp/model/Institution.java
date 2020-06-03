package com.basejava.webapp.model;

import java.util.Comparator;
import java.util.List;

public class Institution {
    private final Link homePage;
    private final List<ExperienceInTheInstitution> experienceDescription;

    private static final Comparator<ExperienceInTheInstitution> COMPARATOR = Comparator.comparing(ExperienceInTheInstitution::getHeading).
            thenComparing(ExperienceInTheInstitution::getStartDate).thenComparing(ExperienceInTheInstitution::getFinishDate).
            thenComparing(ExperienceInTheInstitution::getDescription);

    public Institution(String institutionName, String url, List<ExperienceInTheInstitution> experienceDescription) {
        this.homePage = new Link(institutionName, url);
        this.experienceDescription = experienceDescription;
        experienceDescription.sort(COMPARATOR);
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<ExperienceInTheInstitution> getExperienceDescription() {
        return experienceDescription;
    }
}
