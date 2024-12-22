package com.bnbhms.PayLoad;

import com.bnbhms.Entity.Property;
import com.bnbhms.Entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewsDto {
    private Long id;
    private Integer rating;
    private String description;
    private Property property;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
