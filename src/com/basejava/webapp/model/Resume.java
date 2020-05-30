package com.basejava.webapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private final String fullName;

    private Map<SectionType, Section> sections = new HashMap<>();
    private final Map<ContactType, String> contacts = new HashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setSection(SectionType sectionType, Section section) {
        sections.put(sectionType, section);
    }

    public void setContact(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public Section getSection(SectionType sectionType) {
        return sections.get(sectionType);
    }

    public String getContact(ContactType contactType) {
        return contacts.get(contactType);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Resume resume = (Resume) object;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }
}