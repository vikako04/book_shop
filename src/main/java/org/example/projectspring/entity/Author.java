package org.example.projectspring.entity;



import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer id;

    @Column(name = "full_name")
    private String fullName;


    @Column(name = "years_of_life")
    private String yearsOfLife;

    @Column(name = "years_of_creativity")
    private String yearsOfCreativity;

    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    @Column(name = "photo")
    private String photo;

    @ManyToMany
    @JoinTable(
            name = "author_category",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getYearsOfLife() {
        return yearsOfLife;
    }

    public void setYearsOfLife(String yearsOfLife) {
        this.yearsOfLife = yearsOfLife;
    }

    public String getYearsOfCreativity() {
        return yearsOfCreativity;
    }

    public void setYearsOfCreativity(String yearsOfCreativity) {
        this.yearsOfCreativity = yearsOfCreativity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}