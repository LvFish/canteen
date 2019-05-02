package com.test.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liujiawang on 2019/5/1.
 */
@Entity
@Table(name = "evaluation", schema = "canteen", catalog = "")
public class EvaluationEntity {
    private int evaluationId;
    private String evaluationDetail;
    private Timestamp evaluationTime;
    private int postId;
    private int userId;

    @Id
    @Column(name = "evaluation_id", nullable = false)
    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    @Basic
    @Column(name = "evaluation_detail", nullable = false, length = 255)
    public String getEvaluationDetail() {
        return evaluationDetail;
    }

    public void setEvaluationDetail(String evaluationDetail) {
        this.evaluationDetail = evaluationDetail;
    }

    @Basic
    @Column(name = "evaluation_time", nullable = false)
    public Timestamp getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Timestamp evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    @Basic
    @Column(name = "post_id", nullable = false)
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvaluationEntity that = (EvaluationEntity) o;

        if (evaluationId != that.evaluationId) return false;
        if (postId != that.postId) return false;
        if (userId != that.userId) return false;
        if (evaluationDetail != null ? !evaluationDetail.equals(that.evaluationDetail) : that.evaluationDetail != null)
            return false;
        if (evaluationTime != null ? !evaluationTime.equals(that.evaluationTime) : that.evaluationTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = evaluationId;
        result = 31 * result + (evaluationDetail != null ? evaluationDetail.hashCode() : 0);
        result = 31 * result + (evaluationTime != null ? evaluationTime.hashCode() : 0);
        result = 31 * result + postId;
        result = 31 * result + userId;
        return result;
    }
}
