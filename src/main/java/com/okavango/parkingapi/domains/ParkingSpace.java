package com.okavango.parkingapi.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vacancies")
@EntityListeners(AuditingEntityListener.class)
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true, length = 4)
    private String code;

    @Enumerated(EnumType.STRING)
    private VacancyStatus vacancyStatus;

    @CreatedDate
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime modificationDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    private enum VacancyStatus{
        FREE, OCCUPIED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpace that = (ParkingSpace) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
