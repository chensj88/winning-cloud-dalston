package com.winning.devops.boot.security.model;

import lombok.*;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.model
 * @date: 2019-05-16 14:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Blog {

    private Long id;
    private String name;
    private String content;
}
