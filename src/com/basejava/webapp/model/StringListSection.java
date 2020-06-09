package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class StringListSection extends AbstractSection {

    private final List<String> section;

    public StringListSection(List<String> section) {
        this.section = section;
    }

    public List<String> getSection() {
        return section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringListSection that = (StringListSection) o;
        return section.equals(that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section);
    }
}
