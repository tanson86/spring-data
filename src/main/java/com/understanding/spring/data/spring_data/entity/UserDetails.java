package com.understanding.spring.data.spring_data.entity;

import jakarta.persistence.*;

@Table(name = "user_details")
@IdClass(UserDetailsCK.class)
@Entity
public class UserDetails {
@Id
    private String name;
@Id
    private String address;
    private String phone;

    /*
    While using this approach ***CK.java should be annotated with embeddable. The two @Id fields above and @IdClass is not needed.
     */
//    @EmbeddedId
//    UserDetailsCK userDetailsCK
}
