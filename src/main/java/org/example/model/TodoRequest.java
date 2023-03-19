package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// entity가 아니기 때문에 entity annotation은 따로 붙이지 X
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

    private String title;
    private Long order;
    private Boolean completed;  // 업데이트 할 때 사용?

}
