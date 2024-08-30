package com.example.accessing_data_mysql;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer submissionID;

    private Integer userID;

    private Integer problemID;

    private Result result;  //（Passed, Failed, Error）

    private String submissionCode;

    private Integer lastUpdaterID;

    private LocalDate lastUpdateDate;

    public Integer getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(Integer submissionID) {
        this.submissionID = submissionID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getProblemID() {
        return problemID;
    }

    public void setProblemID(Integer problemID) {
        this.problemID = problemID;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getSubmissionCode() {
        return submissionCode;
    }

    public void setSubmissionCode(String submissionCode) {
        this.submissionCode = submissionCode;
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


enum Result {
    Passed, Failed, Error
}
