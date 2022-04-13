package com.example.todo.entity;

import lombok.*;

import javax.persistence.*;

// JPA -> Java의 표준 ORM


@Entity
@Table(name = "tbl_todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 스펙
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class Todo {






   @Id @GeneratedValue
   private Integer id;

  // @Column(name = "userId")
   private String userId;

   private String title;

   private boolean done;
}
