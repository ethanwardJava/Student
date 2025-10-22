package com.arcade.bootapplication.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @Past
    @Column(name = "birthdate")
    private Date birthdate;

    @PastOrPresent
    @Column(name = "joiningtime")
    private Timestamp joiningtime;


    @Column(name = "isactive")
    private Boolean isactive;

    @Column(name = "mark", columnDefinition = "numeric(3,0)")
    private Integer mark;

    @Column(name = "age")
    private Integer age;

    @Length(min = 2)
    @Column(name = "country", length = 4)
    private String country;

    @Column(name = "price")
    private String price;

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", joiningtime=" + joiningtime +
                ", isactive=" + isactive +
                ", mark=" + mark +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", price=" + price +
                '}';
    }
}


