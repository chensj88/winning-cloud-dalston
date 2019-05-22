package com.winning.devops.boot.security.web;

import com.winning.devops.boot.security.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.web
 * @date: 2019-05-16 15:10
 */
@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping
    public ModelAndView list(Model model){
       model.addAttribute("blogs",blogService.findAllBlogs());
       return new ModelAndView("blog/list","blogModel",model);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/{id}/delete")
    public ModelAndView delete(@PathVariable Long id, Model model){
        blogService.deleteBlogById(id);
        model.addAttribute("blogs",blogService.findAllBlogs());
        return new ModelAndView("blog/list","blogModel",model);
    }

}
