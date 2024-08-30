package com.example.accessing_data_mysql;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Companies")
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer companyID;
    private String companyName;
    private Integer lastUpdaterID;

    private LocalDate lastUpdateDate;

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getLastUpdaterID() {
        return lastUpdaterID;
    }

    public void setLastUpdaterID(Integer lastUpdaterID) {
        this.lastUpdaterID = lastUpdaterID;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
