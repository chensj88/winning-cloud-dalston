package com.winning.devops.boot.security.service;

import com.winning.devops.boot.security.model.Blog;

import java.util.List;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.service
 * @date: 2019-05-16 14:50
 */
public interface IBlogService {
    List<Blog> findAllBlogs();
    void deleteBlogById(Long id);
}
