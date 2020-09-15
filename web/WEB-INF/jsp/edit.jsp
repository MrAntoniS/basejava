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
            <h2><a>${type.title}</a></h2>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <input type="text" name='${type.name()}' size=100 value="${(resume.getSection(type)).getSection()}">
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <textarea name='${type}' cols=82 rows=15>"${(resume.getSection(type)).getSection()}"</textarea>
                </c:when>
<%--                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">--%>
<%--                    <jsp:useBean id="section" class="com.basejava.webapp.model.StringListSection" scope="request"/>--%>
<%--                    <textarea name='${type}' cols=82--%>
<%--                                              rows=15><%=String.join("\n", (section).getSection())%></textarea>--%>
<%--                </c:when>--%>
            </c:choose>
        </c:forEach>

        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
