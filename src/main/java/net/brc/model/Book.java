package net.brc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Book")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title_en;
    private String title_fa;
    private String desc_en;
    private String desc_fa;
    private String authors_en;
    private String authors_fa;
    private Date publishedDate;
    private String bookCategory_en;
    private String bookCategory_fa;
    private String language_en;
    private String language_fa;
    private Integer pages;
    private String isbn;
    private String translator_en;
    private String translator_fa;
}
