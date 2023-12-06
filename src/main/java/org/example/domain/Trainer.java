package org.example.domain;

import org.springframework.stereotype.Component;



public class Trainer {
    private Long id;
    private Long specialization;
    private Long userId;

    public Trainer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Long specialization) {
        this.specialization = specialization;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Trainer(Long id, Long specialization, Long userId) {
        this.id = id;
        this.specialization = specialization;
        this.userId = userId;
    }


}
