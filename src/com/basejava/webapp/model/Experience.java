package com.basejava.webapp.model;

import com.basejava.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static com.basejava.webapp.util.DateUtil.*;
import static com.basejava.webapp.util.DateUtil.NOW;

@XmlAccessorType(XmlAccessType.FIELD)
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Experience EMPTY = new Experience();

    private String heading;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate finishDate;
    private String description;

    public Experience() {
    }

    public Experience(String heading, int startYear, Month startMonth, String description) {
        this(heading, of(startYear, startMonth), NOW, description);
    }

    public Experience(String heading, int startYear, Month startMonth, int finishYear, Month finishMonth, String description) {
        this(heading, of(startYear, startMonth), of(finishYear, finishMonth), description);
    }

    public Experience(String heading, LocalDate startDate, LocalDate finishDate, String description) {
        Objects.requireNonNull(heading, "heading must not be null");
        Objects.requireNonNull(startDate, "start date must not be null");
        Objects.requireNonNull(finishDate, "finish date must not be null");
        this.heading = heading;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description == null ? "" : description;
    }

    public String getHeading() {
        return heading;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return heading.equals(that.heading) &&
                startDate.equals(that.startDate) &&
                finishDate.equals(that.finishDate) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heading, startDate, finishDate, description);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "heading='" + heading + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", description='" + description + '\'' +
                '}';
    }
}


