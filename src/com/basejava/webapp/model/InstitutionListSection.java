package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class InstitutionListSection extends AbstractSection {

    private final List<Institution> section;

    public InstitutionListSection(List<Institution> section) {
        this.section = section;
    }

    public List<Institution> getSection() {
        return section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitutionListSection that = (InstitutionListSection) o;
        return section.equals(that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section);
    }
}
