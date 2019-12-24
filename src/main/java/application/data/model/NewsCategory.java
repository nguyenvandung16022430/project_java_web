package application.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "dbo_news_category")
public class NewsCategory {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "news_category_id")
    @Id
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "newsCategory")
    private List<News> newsList = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Column(name = "created_date")
    private Date createdDate;


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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
