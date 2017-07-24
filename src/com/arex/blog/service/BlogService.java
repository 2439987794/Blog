package com.arex.blog.service;

import java.util.List;

import com.arex.blog.dto.BlogDTO;

public interface BlogService {

	public List<BlogDTO> searchAllBlog();
	public List<BlogDTO> searchAllBlogByUserId(String userId);
	public List<BlogDTO> searchAllBlogByUserName(String userName);
	public BlogDTO searchBlogByBlogId(String blogId);
	public void saveBlog(BlogDTO BlogDTO);
	public void updateBlog(BlogDTO blogDTO);
	public void deleteBlogByBlogId(BlogDTO blogDTO);
	public void halfwayDeleteBlog(BlogDTO blogDTO);
	public List<BlogDTO> searchAllDeletedBlogByUserId(String userId);
	public void restoreBlog(BlogDTO blogDTO);
}
