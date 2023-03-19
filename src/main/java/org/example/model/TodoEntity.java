package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)   // title이 없으면 db에 의미가 없기 때문
    private String title;

    @Column(name = "todoOrder", nullable = false)   // order가 h2DB에서 예약어로 사용되고 있기 때문에 따로 컬럼명 지정
    private Long order;

    @Column(nullable = false)
    private Boolean completed;
}
