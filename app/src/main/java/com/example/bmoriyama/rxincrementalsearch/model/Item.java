package com.example.bmoriyama.rxincrementalsearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @SerializedName("tags")
    private List<String> tags = null;

    @SerializedName("owner")
    private Owner owner;

    @SerializedName("is_answered")
    private Boolean isAnswered;

    @SerializedName("view_count")
    private Integer viewCount;

    @SerializedName("closed_date")
    private Integer closedDate;

    @SerializedName("answer_count")
    private Integer answerCount;

    @SerializedName("score")
    private Integer score;

    @SerializedName("last_activity_date")
    private Integer lastActivityDate;

    @SerializedName("creation_date")
    private Integer creationDate;

    @SerializedName("last_edit_date")
    private Integer lastEditDate;

    @SerializedName("question_id")
    private Integer questionId;

    @SerializedName("link")
    private String link;

    @SerializedName("closed_reason")
    private String closedReason;

    @SerializedName("title")
    private String title;

    @SerializedName("accepted_answer_id")
    private Integer acceptedAnswerId;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(Boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Integer closedDate) {
        this.closedDate = closedDate;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Integer lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Integer lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getClosedReason() {
        return closedReason;
    }

    public void setClosedReason(String closedReason) {
        this.closedReason = closedReason;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public void setAcceptedAnswerId(Integer acceptedAnswerId) {
        this.acceptedAnswerId = acceptedAnswerId;
    }
}
