package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static com.basejava.webapp.model.ContactType.*;
import static com.basejava.webapp.model.SectionType.*;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume testResume = new Resume("Григорий Кислин");

        testResume.setContact(PHONE_NUMBER, "+7(921) 855-0482");
        testResume.setContact(SKYPE, "skype:grigory.kislin");
        testResume.setContact(E_MAIL, "gkislin@yandex.ru");
        testResume.setContact(LINKED_IN, "https://www.linkedin.com/in/gkislin");
        testResume.setContact(GIT_HUB, "https://github.com/gkislin");
        testResume.setContact(STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        testResume.setContact(HOMEPAGE, "http://gkislin.ru/");

        testResume.setSection(OBJECTIVE, new SectionAsString("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        testResume.setSection(PERSONAL, new SectionAsString("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> achievement = new ArrayList<>();
        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, " +
                "Jira, Zendesk.");
        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP." +
                " Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                "интеграция CIFS/SMB java сервера.");
        achievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5," +
                " Highstock для алгоритмического трейдинга.");
        achievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        testResume.setSection(ACHIEVEMENT, new SectionAsStringList(achievement));

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), " +
                "JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, " +
                "Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        testResume.setSection(QUALIFICATIONS, new SectionAsStringList(qualifications));

        List<Institution> experience = new ArrayList<>();
        experience.add(new Institution("Автор проекта", "Java Online Projects", "http://javaops.ru/", YearMonth.of(2013, 10), YearMonth.now(),
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experience.add(new Institution("Старший разработчик (backend)", "Wrike" , "https://www.wrike.com/", YearMonth.of(2014, 10), YearMonth.of(2016, 1),
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                        " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        experience.add(new Institution("Java архитектор", "RIT Center", "-", YearMonth.of(2012, 4), YearMonth.of(2014, 10),
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                        "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), " +
                        "сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, " +
                        "Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        testResume.setSection(EXPERIENCE, new SectionAsInstitutionList(experience));

        List<Institution> education = new ArrayList<>();
        education.add(new Institution("Закончил с отличием", "Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/", YearMonth.of(1984, 9),
                YearMonth.of(1987, 6), " "));
        education.add(new Institution("Инженер (программист Fortran, C)", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "https://itmo.ru/ru/", YearMonth.of(1987, 9), YearMonth.of(1993, 7), " "));
        education.add(new Institution("Аспирантура (программист С, С++)", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "https://itmo.ru/ru/", YearMonth.of(1993, 9), YearMonth.of(1996, 7), " "));
        testResume.setSection(EDUCATION, new SectionAsInstitutionList(education));

        System.out.println(testResume.getContact(PHONE_NUMBER));
        System.out.println(testResume.getSection(QUALIFICATIONS));
    }
}