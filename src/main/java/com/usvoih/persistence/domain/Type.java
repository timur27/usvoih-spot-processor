package com.usvoih.persistence.domain;

import com.usvoih.dto.Category;
import com.usvoih.dto.Unique;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"category", "subcategory"})})
public class Type {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String subcategory;
}