package com.basejava.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Телефон"),
    SKYPE("Skype"){
        @Override
        public String toHtml0(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    E_MAIL("Электронная почта"){
        @Override
        public String toHtml0(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    LINKED_IN("LinkedIn"),
    GIT_HUB("GitHub"),
    STACKOVERFLOW("Stackoverflow"),
    HOMEPAGE("Домашняя страница");


    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }
}
