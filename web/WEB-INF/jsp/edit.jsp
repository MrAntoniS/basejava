<%@ page import="com.basejava.webapp.model.*" %>
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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.basejava.webapp.model.AbstractSection"/>
            <h2><a>${type.title}</a></h2>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <input type="text" name='${type.name()}' size=100 value='<%=((StringSection) section).getSection()%>'>
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <textarea name='${type}' cols=82 rows=15><%=((StringSection) section).getSection()%></textarea>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <textarea name='${type}' cols=82
                                              rows=15><%=String.join("\n", ((StringListSection)section).getSection())%></textarea>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="institution" items="<%=((InstitutionListSection) section).getSection()%>">
                        <dl>
                            <dt>Учереждение:</dt>
                            <dd><input type="text" name='${type}' size=50 value="${institution.homePage.name}"></dd>
                        </dl>
                        <dl>
                            <dt>Сайт:</dt>
                            <dd><input type="text" name='${type}url' size=30 value="${institution.homePage.url}"></dd>
                        </dl>
                        <c:forEach var="experience" items="${institution.experienceDescription}">
                            <jsp:useBean id="experience" type="com.basejava.webapp.model.Experience"/>
                            <dl>
                                <dt>Начальная дата:</dt>
                                <dd><input type="text" name="${type}startDate" size=15
                                           value="<%=experience.getStartDate().toString()%>"></dd>
                            </dl>
                            <dl>
                                <dt>Конечная дата:</dt>
                                <dd><input type="text" name="${type}finishDate" size=15
                                           value="<%=experience.getFinishDate().toString()%>"></dd>
                            </dl>
                            <dl>
                                <dt>Должность:</dt>
                                <dd><input type="text" name='${type}heading' size=50
                                           value="${experience.heading}">
                            </dl>
                            <dl>
                                <dt>Описание:</dt>
                                <dd><textarea name="${type}description" rows=5
                                              cols=75>${experience.description}</textarea></dd>
                            </dl>
                            <br>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>

        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
