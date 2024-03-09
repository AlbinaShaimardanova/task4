package org.example.dto;

import java.util.Date;

public class Model {
    private String login;
    private String surname;
    private String name;
    private String patronyc;
    private Date accessDate;
    private String application;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronyc() {
        return patronyc;
    }

    public void setPatronyc(String patronyc) {
        this.patronyc = patronyc;
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "{" + login + "::" + surname + "::" + name + "::" + patronyc + "::" + accessDate + "::" + application + "}";
    }
}
