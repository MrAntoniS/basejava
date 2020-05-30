package com.basejava.webapp.model;

import java.time.YearMonth;

public class Institution {
    private final String heading;
    private final String institutionName;
    private final String url;
    private final YearMonth startDate;
    private final YearMonth finishDate;
    private final String description;

    public Institution(String heading, String institutionName, String url, YearMonth startDate, YearMonth finishDate, String description) {
        this.heading = heading;
        this.institutionName = institutionName;
        this.url = url;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description;
    }

    public String getHeading() {
        return heading;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getUrl() {
        return url;
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
