package net.brc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Reader")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    //relationship
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ReaderExam",
            joinColumns = {
                    @JoinColumn(name = "readerId", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "examId", referencedColumnName = "id")
            })
    private List<Exam> exam = new ArrayList<>();

}
