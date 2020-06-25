package com.basejava.webapp.storage.strategy;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.basejava.webapp.model.SectionType.*;

public class DataStreamSerializer implements SerializationStrategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections
            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                AbstractSection section = entry.getValue();
                SectionType sectionType = entry.getKey();
                dos.writeUTF(entry.getKey().name());
                if (sectionType == OBJECTIVE || sectionType == PERSONAL) {
                    dos.writeUTF(((StringSection) section).getSection());
                }
                if (sectionType == ACHIEVEMENT || sectionType == QUALIFICATIONS) {
                    List<String> listSection = ((StringListSection) section).getSection();
                    dos.writeInt(listSection.size());
                    for (String s : listSection) {
                        dos.writeUTF(s);
                    }
                }
                if (sectionType == EXPERIENCE || sectionType == EDUCATION) {
                    List<Institution> institutionListSection = ((InstitutionListSection) section).getSection();
                    dos.writeInt(institutionListSection.size());
                    for (Institution i : institutionListSection) {
                        dos.writeUTF(i.getHomePage().getName());
                        dos.writeUTF(i.getHomePage().getUrl());
                        List<Experience> experience = i.getExperienceDescription();
                        dos.writeInt(experience.size());
                        for (Experience e : experience) {
                            dos.writeUTF(e.getHeading());
                            dos.writeUTF(e.getStartDate().toString());
                            dos.writeUTF(e.getFinishDate().toString());
                            dos.writeUTF(e.getDescription());
                        }
                    }
                }
            }
        }

    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                if (sectionType == OBJECTIVE || sectionType == PERSONAL) {
                    resume.setSection(sectionType, new StringSection(dis.readUTF()));
                }
                if (sectionType == ACHIEVEMENT || sectionType == QUALIFICATIONS) {
                    int listSectionSize = dis.readInt();
                    List<String> listSection = new ArrayList<>();
                    for (int z = 0; z < listSectionSize; z++) {
                        listSection.add(dis.readUTF());
                    }
                    resume.setSection(sectionType, new StringListSection(listSection));
                }
                if (sectionType == EXPERIENCE || sectionType == EDUCATION) {
                    int institutionListSectionSize = dis.readInt();
                    List<Institution> institutionListSection = new ArrayList<>();
                    for (int z = 0; z < institutionListSectionSize; z++) {
                        String name = dis.readUTF();
                        String url = dis.readUTF();
                        List<Experience> experience = new ArrayList<>();
                        int experienceSize = dis.readInt();
                        for (int y = 0; y < experienceSize; y++) {
                            String heading = dis.readUTF();
                            LocalDate startDate = LocalDate.parse(dis.readUTF());
                            LocalDate finishDate = LocalDate.parse(dis.readUTF());
                            String description = dis.readUTF();
                            experience.add(new Experience(heading, startDate, finishDate, description));
                        }
                        institutionListSection.add(new Institution(new Link(name, url), experience));
                    }
                    resume.setSection(sectionType, new InstitutionListSection(institutionListSection));
                }
            }
            return resume;
        }
    }
}
