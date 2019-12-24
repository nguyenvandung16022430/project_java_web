package application.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "dbo_news")
public class News {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "news_id")
    @Id
    private int id;

    @Column(name = "news_category_id")
    private int newsCategoryId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "news_category_id", insertable = false, updatable = false)
    private NewsCategory newsCategory;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "news_name")
    private String newsName;

    @Column(name = "news_content")
    private String content;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "news")
    private List<NewsImage> newsImageList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewsCategoryId() {
        return newsCategoryId;
    }

    public void setNewsCategoryId(int newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<NewsImage> getNewsImageList() {
        return newsImageList;
    }

    public void setNewsImageList(List<NewsImage> newsImageList) {
        this.newsImageList = newsImageList;
    }
}
