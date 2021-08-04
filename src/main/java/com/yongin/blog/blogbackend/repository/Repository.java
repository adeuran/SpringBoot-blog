package com.yongin.blog.blogbackend.repository;

import com.yongin.blog.blogbackend.dao.BoardDao;

import java.util.List;

public interface Repository {
    BoardDao save(BoardDao boardDao);
    List<BoardDao> findAll();
}
