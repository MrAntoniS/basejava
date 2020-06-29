package com.basejava.webapp.storage.strategy;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements SerializationStrategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            writeCollection(dos, r.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeCollection(dos, r.getSections().entrySet(), entry -> {
                AbstractSection section = entry.getValue();
                SectionType sectionType = entry.getKey();
                dos.writeUTF(entry.getKey().name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((StringSection) section).getSection());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((StringListSection) section).getSection(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((InstitutionListSection) section).getSection(), instListSection -> {
                            dos.writeUTF(instListSection.getHomePage().getName());
                            dos.writeUTF(instListSection.getHomePage().getUrl());
                            writeCollection(dos, instListSection.getExperienceDescription(), exp -> {
                                dos.writeUTF(exp.getHeading());
                                dos.writeUTF(exp.getStartDate().toString());
                                dos.writeUTF(exp.getFinishDate().toString());
                                dos.writeUTF(exp.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, CollectionWriter<T> collectionWriter) throws IOException {
        dos.writeInt(collection.size());
        for (T type : collection) {
            collectionWriter.accept(type);
        }
    }

    private interface CollectionWriter<T> {
        void accept(T type) throws IOException;
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readResumeField(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readResumeField(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.setSection(sectionType, new StringSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.setSection(sectionType, new StringListSection(readListCollection(dis, dis::readUTF)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.setSection(sectionType, new InstitutionListSection(readListCollection(dis, () ->
                                new Institution(new Link(dis.readUTF(), dis.readUTF()), DataStreamSerializer.this.readListCollection(dis, () ->
                                        new Experience(dis.readUTF(), LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()), dis.readUTF()))))));
                        break;
                }
            });
            return resume;
        }
    }

    private <T> List<T> readListCollection(DataInputStream dis, ListCollectionReader<T> listCollectionReader) throws IOException {
        List<T> list = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            list.add(listCollectionReader.accept());
        }
        return list;
    }

    private interface ListCollectionReader<T> {
        T accept() throws IOException;
    }

    private void readResumeField(DataInputStream dis, ResumeFieldReader resumeFieldReader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            resumeFieldReader.accept();
        }
    }

    private interface ResumeFieldReader {
        void accept() throws IOException;
    }
}