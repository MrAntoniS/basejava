package com.basejava.webapp.model;

import java.util.List;

public class SectionAsStringList extends Section {

    private final List<String> section;

    public SectionAsStringList(List<String> section) {
        this.section = section;
    }

    public List<String> getSection() {
        return section;
    }
}
