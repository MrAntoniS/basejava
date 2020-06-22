package com.basejava.webapp.storage.strategy;

import com.basejava.webapp.model.*;

import java.io.*;
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
            return resume;
        }
    }
}
