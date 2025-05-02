package com.example.AkademyTasks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookUpdateRequest {

    @NotBlank
    @Size(min = 2)
    private String target;

    @NotBlank
    @Size(min = 5)
    private String newTitle;

    @NotBlank
    private String newAuthorName;

    public BookUpdateRequest(String target, String newTitle, String newAuthorName) {
        this.target = target;
        this.newTitle = newTitle;
        this.newAuthorName = newAuthorName;
    }

    public BookUpdateRequest() {
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getNewAuthorName() {
        return newAuthorName;
    }

    public void setNewAuthorName(String newAuthorName) {
        this.newAuthorName = newAuthorName;
    }
}
