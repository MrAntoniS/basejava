package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.Storage;
import com.basejava.webapp.util.DateUtil;
import com.basejava.webapp.util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

        final boolean isCreate = (uuid == null || uuid.length() == 0);
        Resume r;
        if (isCreate) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (HtmlUtil.isEmpty(value)) {
                r.getContacts().remove(type);
            } else {
                r.setContact(type, value);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (HtmlUtil.isEmpty(value) && values.length < 2) {
                r.getSections().remove(type);
            } else {
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
                        String[] urls = request.getParameterValues(type.name() + "url");
                        List<Institution> institutions = new ArrayList<>();
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (!HtmlUtil.isEmpty(name)) {
                                List<Experience> experiences = new ArrayList<>();
                                String[] headings = request.getParameterValues(type.name() + i + "heading");
                                String[] startDates = request.getParameterValues(type.name() + i + "startDate");
                                String[] finishDates = request.getParameterValues(type.name() + i + "finishDate");
                                String[] descriptions = request.getParameterValues(type.name() + i + "description");
                                for (int j = 0; j < headings.length; j++) {
                                    if (!HtmlUtil.isEmpty(headings[j])) {
                                        experiences.add(new Experience(headings[j], DateUtil.parse(startDates[j]), DateUtil.parse(finishDates[j]), descriptions[j]));

                                    }
                                }
                                institutions.add(new Institution(new Link(name, urls[i]), experiences));
                            }
                        }
                        r.setSection(type, new InstitutionListSection(institutions));
                        break;
                }
            }
        }
        if (isCreate) {
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
            case "add":
                r = Resume.EMPTY;
                break;
            case "edit":
                r = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    AbstractSection section = r.getSection(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = StringSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = StringListSection.EMPTY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            InstitutionListSection instSection = (InstitutionListSection) section;
                            List<Institution> emptyFirstInstitutions = new ArrayList<>();
                            emptyFirstInstitutions.add(Institution.EMPTY);
                            if (instSection != null) {
                                for (Institution inst : instSection.getSection()) {
                                    List<Experience> emptyFirstExperience = new ArrayList<>();
                                    emptyFirstExperience.add(Experience.EMPTY);
                                    emptyFirstExperience.addAll(inst.getExperienceDescription());
                                    emptyFirstInstitutions.add(new Institution(inst.getHomePage(), emptyFirstExperience));
                                }
                            }
                            section = new InstitutionListSection(emptyFirstInstitutions);
                            break;
                    }
                    r.setSection(type, section);
                }
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
