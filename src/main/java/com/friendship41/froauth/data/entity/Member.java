package com.friendship41.froauth.data.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEMP_MEMBER")
public class Member implements Serializable {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String username;
  private String remark;

  public Member(String name, String username, String remark) {
    this.name = name;
    this.username = username;
    this.remark = remark;
  }
}
