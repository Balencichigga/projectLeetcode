package com.example.accessing_data_mysql;

import jakarta.persistence.*;
@Entity
@Table(name = "ProblemCompanies")
public class ProblemCompany {
    @Id
    private Integer problemID;

    private Integer companyID;

    public Integer getProblemID() {
        return problemID;
    }
    public Integer getCompanyID() {
        return companyID;
    }

    public void setProblemID(Integer problemID) {
        this.problemID = problemID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

}
