package com.clover.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "code")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Code extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Code group;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "description")
    private String description;

    @Builder
    public Code(Code group, String code, String  value, String description) {
        this.group = group;
        this.code = code;
        this.value = value;
        this.description = description;
    }
}
