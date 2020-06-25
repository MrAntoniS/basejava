package com.basejava.webapp.model;

import java.util.Objects;

public class StringSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

    private String section;

    public StringSection(String section) {
        this.section = section;
    }

    public StringSection() {
    }

    public String getSection() {
        return section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSection that = (StringSection) o;
        return section.equals(that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section);
    }

    @Override
    public String toString() {
        return "StringSection{" +
                "section='" + section + '\'' +
                '}';
    }
}
