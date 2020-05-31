package com.basejava.webapp.model;

import java.util.List;

public class InstitutionListSection extends AbstractSection {

    private final List<Institution> section;

    public InstitutionListSection(List<Institution> section) {
        this.section = section;
    }

    public List<Institution> getSection() {
        return section;
    }
}
