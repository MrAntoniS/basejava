package com.basejava.webapp.model;

import java.time.YearMonth;

public class ExperienceInTheInstitution {
    private final String heading;
    private final YearMonth startDate;
    private final YearMonth finishDate;
    private final String description;

    public ExperienceInTheInstitution(String heading, YearMonth startDate, YearMonth finishDate, String description) {
        this.heading = heading;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description;
    }

    public String getHeading() {
        return heading;
    }

    public YearMonth getStartDate() {
        return startDate;
    }

    public YearMonth getFinishDate() {
        return finishDate;
    }

    public String getDescription() {
        return description;
    }
}


