package net.brc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "Reader")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reader {
//public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fullName_en;
    private String fullName_fa;
    private char gender;
    private Date dob;
    private String mobile;
    @NotBlank
    private String mail;
    private String password;
    private String district_en;
    private String district_fa;
    private String province_en;
    private String province_fa;
    private boolean participated;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ReaderRoles",
    joinColumns = {@JoinColumn(name = "readerId")},
    inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<Role> authorities;

}
