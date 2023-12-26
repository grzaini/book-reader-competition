package net.brc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Exam")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Date examDate;
    private String status_en;
    private String status_fa;
    @Lob
    private String information;

    //relationship
    @OneToOne(targetEntity = Book.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    private Book book;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Reader> reader = new ArrayList<>();
//
//    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "examId", referencedColumnName = "id")
//    private List<Question> question = new ArrayList<>();
}
