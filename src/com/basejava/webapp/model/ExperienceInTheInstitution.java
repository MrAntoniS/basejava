package com.basejava.webapp.model;

import java.time.YearMonth;
import java.util.Objects;

public class ExperienceInTheInstitution {
    private final String heading;
    private final YearMonth startDate;
    private final YearMonth finishDate;
    private final String description;

    public ExperienceInTheInstitution(String heading, YearMonth startDate, YearMonth finishDate, String description) {
        Objects.requireNonNull(heading, "heading must not be null");
        Objects.requireNonNull(startDate, "start date must not be null");
        Objects.requireNonNull(finishDate, "finish date must not be null");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceInTheInstitution that = (ExperienceInTheInstitution) o;
        return heading.equals(that.heading) &&
                startDate.equals(that.startDate) &&
                finishDate.equals(that.finishDate) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heading, startDate, finishDate, description);
    }
}


