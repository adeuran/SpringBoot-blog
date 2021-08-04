package com.yongin.blog.blogbackend.repository;

import com.yongin.blog.blogbackend.dao.BoardDao;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface BoardRepository extends JpaRepository<BoardDao, Long>{

}
