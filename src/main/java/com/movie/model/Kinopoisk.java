package com.movie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name="kinopoisk_top_list")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Kinopoisk {
    @Setter
    @Getter
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="id")
    private UUID id;

    @Setter
    @Getter
    @Column(name="ru_name")
    private String ruName;

    @Setter
    @Getter
    @Column(name="en_name")
    private String enName;

    @Setter
    @Getter
    @Column(name="rating")
    private String rating;

    @Setter
    @Getter
    @Column(name="url")
    private String url;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Getter
    @Column(name="date_created")
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Getter
    @Column(name="date_updated")
    private Date dateUpdated;

}

