<%@ page import="com.basejava.webapp.model.StringSection" %>
<%@ page import="com.basejava.webapp.model.StringListSection" %>
<%@ page import="com.basejava.webapp.model.InstitutionListSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.basejava.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <table>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.basejava.webapp.model.SectionType, com.basejava.webapp.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.basejava.webapp.model.AbstractSection"/>
            <tr>
                <td>
                    <h3><a name="type.name">${type.title}</a></h3>
                </td>
            </tr>
            <c:choose>
                <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                    <tr>
                        <td>
                            <p><%=((StringSection) section).getSection()%></p>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <tr>
                        <td>
                            <ul>
                                <c:forEach var="field" items="<%=((StringListSection) section).getSection()%>">
                                    <li>${field}</li>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="institution" items="<%=((InstitutionListSection) section).getSection()%>">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${empty institution.homePage.url}">
                                        <h3>${institution.homePage.name}</h3>
                                    </c:when>
                                    <c:otherwise>
                                        <h3><a href="${institution.homePage.url}">${institution.homePage.name}</a></h3>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <c:forEach var="expirience" items="${institution.experienceDescription}">
                            <jsp:useBean id="expirience" type="com.basejava.webapp.model.Experience"/>
                            <tr>
                                <td><b>${expirience.heading}</b><b> c </b><b>${expirience.startDate.toString()}</b><b>
                                    по </b><b>${expirience.finishDate.toString()}</b><br>${expirience.description}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>