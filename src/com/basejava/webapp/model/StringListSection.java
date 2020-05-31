package com.basejava.webapp.model;

import java.util.List;

public class StringListSection extends AbstractSection {

    private final List<String> section;

    public StringListSection(List<String> section) {
        this.section = section;
    }

    public List<String> getSection() {
        return section;
    }
}
