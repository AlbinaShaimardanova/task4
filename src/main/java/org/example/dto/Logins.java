package org.example.dto;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logins_generator")
    @SequenceGenerator(name = "logins_generator", sequenceName = "seq_login_id", allocationSize = 1)
    int id;
    Date access_date;
    int user_id;
    String application;

    public Logins(int id, Date access_date, int user_id, String application) {
        this.id = id;
        this.access_date = access_date;
        this.user_id = user_id;
        this.application = application;
    }

    public Logins() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAccess_date() {
        return access_date;
    }

    public void setAccess_date(Date access_date) {
        this.access_date = access_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Logins{" +
                "id=" + id +
                ", access_date=" + access_date +
                ", user_id=" + user_id +
                ", application='" + application + '\'' +
                '}';
    }
}
