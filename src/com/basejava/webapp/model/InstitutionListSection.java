package com.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class InstitutionListSection extends AbstractSection {

    private static final long serialVersionUID = 1L;

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
