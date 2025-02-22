package com.understanding.spring.data.spring_data.entity;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
public class UserDetailsCK implements Serializable {
    private String name;
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsCK that = (UserDetailsCK) o;
        return Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
