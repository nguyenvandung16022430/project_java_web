package application.model.viewModel.common;

import java.util.Date;

public class CategoryVM {

    private int id;
    private String name;
    private String shortDesc;
    private Date cteatedDate;


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

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Date getCteatedDate() {
        return cteatedDate;
    }

    public void setCteatedDate(Date cteatedDate) {
        this.cteatedDate = cteatedDate;
    }
}
