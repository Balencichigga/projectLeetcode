package com.example.accessing_data_mysql;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer topicID;

    private String topicName;

    private Integer lastUpdaterID;

    private LocalDate lastUpdateDate;

    public Integer getTopicID() {
        return topicID;
    }

    public void setTopicID(Integer topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
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
