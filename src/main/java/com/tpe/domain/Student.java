package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name can not be null")
    @NotBlank(message = "First name can not be white space")
    @Size(min=1,max=100,message = "First name '${validatedValue}' must be between {min} and {max} chars long")
    @Column(length = 100,nullable = false)
    private String firstName;

    @Column(length = 100,nullable = false)
    private String lastName;

    private Integer grade;

    @Column(length = 14)
    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please provide valid phone number")
    private String phoneNumber;

    @Column(length = 100,nullable = false,unique = true)
    @Email(message = "Provide valid email")
    private String email;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss",timezone ="Turkey")
    private LocalDateTime createDate=LocalDateTime.now();

    @OneToMany(mappedBy = "student")
    private List<Book> books = new ArrayList<>();


}
