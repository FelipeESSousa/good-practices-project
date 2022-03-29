package br.com.goodpractices.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AuditObject {

    @Column(name = "dat_created")
    private LocalDateTime createdAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
