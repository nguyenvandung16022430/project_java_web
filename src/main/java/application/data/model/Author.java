package application.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "dbo_author")
public class Author {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    @Id
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<AuthorProduct> authorProductList = new ArrayList<>();

    @Column(name = "author_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorProduct> getAuthorProductList() {
        return authorProductList;
    }

    public void setAuthorProductList(List<AuthorProduct> authorProductList) {
        this.authorProductList = authorProductList;
    }
}
