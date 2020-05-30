package com.basejava.webapp.model;

import java.util.List;

public class SectionAsInstitutionList extends Section {

    private final List<Institution> section;

    public SectionAsInstitutionList(List<Institution> section) {
        this.section = section;
    }

    public List<Institution> getSection() {
        return section;
    }
}
