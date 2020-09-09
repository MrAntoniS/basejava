package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
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
                if (type == OBJECTIVE || type == PERSONAL) {
                    r.setSection(type, new StringSection(value));
                    break;
                }
                if (type == ACHIEVEMENT || type == QUALIFICATIONS) {
                    r.setSection(type, new StringListSection(Arrays.asList(value.split("\n"))));
                    break;
                }
                if (type == EXPERIENCE || type == EDUCATION) {
                    String[] values = request.getParameterValues(type.name());
                    List<Institution> institutions = new ArrayList<>();
                    for (String name : values) {
                        if (name != null && name.trim().length() != 0) {
                            String url = request.getParameter("url");
                            String heading = request.getParameter("heading");
                            String startDate = request.getParameter("startDate");
                            String finishDate = request.getParameter("finishDate");
                            String description = request.getParameter("description");
                            institutions.add(new Institution(name, url, new Experience(heading, LocalDate.parse(startDate), LocalDate.parse(finishDate), description)));
                        }
                    }
                    r.setSection(type, new InstitutionListSection(institutions));
                    break;
                }
            } else {
                r.getSections().remove(type);
            }
        }
        storage.update(r);
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
            case "edit":
                r = storage.get(uuid);
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
