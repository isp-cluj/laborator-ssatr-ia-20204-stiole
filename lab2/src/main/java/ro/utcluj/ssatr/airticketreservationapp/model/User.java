/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.utcluj.ssatr.airticketreservationapp.model;

import java.util.Objects;

/**
 *
 * @author mihai
 */
public class User {
    private String usedId;
    private String name;

    public User(String usedId, String name) {
        this.usedId = usedId;
        this.name = name;
    }

    public String getUsedId() {
        return usedId;
    }

    public String getName() {
        return name;
    }

    public void setUsedId(String usedId) {
        this.usedId = usedId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "usedId='" + usedId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(usedId, user.usedId) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usedId, name);
    }
}
