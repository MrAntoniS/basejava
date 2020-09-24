package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.Storage;
import com.basejava.webapp.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.basejava.webapp.model.ContactType.*;
import static com.basejava.webapp.model.SectionType.*;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        boolean notExist = uuid.equals("");
        Resume r;
        if (notExist) {
            r = new Resume(UUID.randomUUID().toString(), fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.setContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        r.setSection(type, new StringSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        String[] fields = value.split("\n");
                        for (String field : fields) {
                            if (field.trim().length() != 0) {
                                list.add(field);
                            }
                        }
                        r.setSection(type, new StringListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        String[] names = request.getParameterValues(type.name());
                        String[] urls = request.getParameterValues(type.name() + "url");
                        List<Institution> institutions = new ArrayList<>();
                        for (int j = 0; j < names.length; j++) {
                            if (names[j] != null && names[j].trim().length() != 0) {
                                List<Experience> experiences = new ArrayList<>();
                                String[] headings = request.getParameterValues(type.name() + j + "heading");
                                String[] startDates = request.getParameterValues(type.name() + j + "startDate");
                                String[] finishDates = request.getParameterValues(type.name() + j + "finishDate");
                                String[] descriptions = request.getParameterValues(type.name() + j + "description");
                                for (int i = 0; i < headings.length; i++) {
                                    if (headings[i] != null && headings[i].trim().length() != 0) {
                                        experiences.add(new Experience(headings[i], DateUtil.parse(startDates[i]), DateUtil.parse(finishDates[i]), descriptions[i]));

                                    }
                                }
                                institutions.add(new Institution(new Link(names[j], urls[j]), experiences));
                            }
                        }
                        r.setSection(type, new InstitutionListSection(institutions));
                        break;
                }
            } else {
                r.getSections().remove(type);
            }
        }
        if (notExist) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                r = storage.get(uuid);
                break;
            case "edit":
                r = storage.get(uuid);
                for (SectionType sectionType : new SectionType[]{OBJECTIVE, PERSONAL, ACHIEVEMENT, QUALIFICATIONS, EXPERIENCE, EDUCATION}) {
                    switch (sectionType) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (r.getSection(sectionType) == null) {
                                r.setSection(sectionType, new StringSection(""));
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (r.getSection(sectionType) == null) {
                                r.setSection(sectionType, new StringListSection(Arrays.asList("".split("\n"))));
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            if (r.getSection(sectionType) == null) {
                                List<Institution> institutions = new ArrayList<>();
                                institutions.add(new Institution("", null, new Experience("", DateUtil.NOW, DateUtil.NOW, "")));
                                r.setSection(sectionType, new InstitutionListSection(institutions));
                            } else {
                                InstitutionListSection ils = (InstitutionListSection) r.getSection(sectionType);
                                List<Institution> institutions = ils.getSection();
                                for (int i = 0; i < institutions.size(); i++) {
                                    Institution inst = institutions.get(i);
                                    if (inst.getHomePage().getName().trim().length() != 0 && i == institutions.size() - 1) {
                                        institutions.add(new Institution("", null, new Experience("", DateUtil.NOW, DateUtil.NOW, "")));
                                    }
                                }
                                r.setSection(sectionType, new InstitutionListSection(institutions));
                            }
                            break;
                    }
                }
                storage.update(r);
                break;
            case "add":
                r = new Resume("", "");

                r.setContact(PHONE_NUMBER, "");
                r.setContact(SKYPE, "");
                r.setContact(E_MAIL, "");
                r.setContact(LINKED_IN, "");
                r.setContact(GIT_HUB, "");
                r.setContact(STACKOVERFLOW, "");
                r.setContact(HOMEPAGE, "");

                r.setSection(OBJECTIVE, new StringSection(""));
                r.setSection(PERSONAL, new StringSection(""));

                r.setSection(ACHIEVEMENT, new StringListSection(Arrays.asList("".split("\n"))));
                r.setSection(QUALIFICATIONS, new StringListSection(Arrays.asList("".split("\n"))));

                List<Institution> experience = new ArrayList<>();
                experience.add(new Institution("", null, new Experience("", DateUtil.NOW, DateUtil.NOW, "")));
                r.setSection(EXPERIENCE, new InstitutionListSection(experience));

                List<Institution> education = new ArrayList<>();
                education.add(new Institution("", null, new Experience("", DateUtil.NOW, DateUtil.NOW, "")));
                r.setSection(EDUCATION, new InstitutionListSection(education));
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}
