package com.example.accessing_data_mysql;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LeetCodeProblems")
public class Problem {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer problemID;

    private String problemName;

    private Difficulty difficulty;

    private String description;

    private LocalDate lastUpdateDate;

    private Integer lastUpdaterID;

    public int getProblemID() {
        return problemID;
    }

    public void setProblemID(Integer problemID) {
        this.problemID = problemID;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdaterID() {
        return lastUpdaterID;
    }

    public void setLastUpdaterID(Integer lastUpdaterID) {
        this.lastUpdaterID = lastUpdaterID;
    }
}

// Enum
enum Difficulty {
    Easy, Medium, Hard
}
