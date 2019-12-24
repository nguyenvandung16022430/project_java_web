package application.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "dbo_news_image")
public class NewsImage {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "news_image_id")
    @Id
    private int id;

    @Column(name = "news_id", insertable = false, updatable = false)
    private int newsId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;

    @Column(name = "link")
    private String link;

    @Column(name = "created_date")
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
