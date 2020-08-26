package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    public void init() {
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        String name = request.getParameter("name");
        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + "!");
        Writer writer = response.getWriter();
        writer.write("<html>\n" +
                        "<head>\n" +
                        "<style>\n" +
                        "table, th, td {\n" +
                        "  border: 1px solid black;\n" +
                        "  border-collapse: collapse;\n" +
                        "}\n" +
                        "th, td {\n" +
                        "  padding: 5px;\n" +
                        "  text-align: left;\n" +
                        "}\n" +
                        "</style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<table style=\"width:50%\">\n" +
                        "  <h2>Список резюме</h2>\n" +
                        "  <tr>\n" +
                        "    <th>UUID</th>\n" +
                        "    <th>Full Name</th>\n" +
                        "  </tr>\n"
        );

        for(Resume resume: storage.getAllSorted()) {
            writer.write("  </tr>\n" +
                            "    <td>"+resume.getUuid()+"</td>\n" +
                            "    <td>"+resume.getFullName()+"</td>\n" +
                            "  </tr>\n");
        }

        writer.write("</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }
}
