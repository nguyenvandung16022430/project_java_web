package application.data.model;

import javax.persistence.*;

@Entity(name = "dbo_status")
public class Status {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "status_id")
    @Id
    private int id;


    @Column(name = "status_name")
    private String statusName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
