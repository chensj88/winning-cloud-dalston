package com.winning.devops.boot.security.service.impl;

import com.winning.devops.boot.security.model.Blog;
import com.winning.devops.boot.security.service.IBlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.service.impl
 * @date: 2019-05-16 15:00
 */
@Service
public class BlogServiceImpl implements IBlogService {
    private List<Blog> blogs = new ArrayList<>();

    public BlogServiceImpl() {
        blogs.add(new Blog(1L,"blog test 1","blog 1"));
        blogs.add(new Blog(2L,"blog test 2","blog 2"));
    }

    @Override
    public List<Blog> findAllBlogs() {
        return blogs;
    }

    @Override
    public void deleteBlogById(Long id) {
        // 替换 iterator中的remove方法
        blogs.removeIf(blog -> id.equals(blog.getId()));
    }
}
