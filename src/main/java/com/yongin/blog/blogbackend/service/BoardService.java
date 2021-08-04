package com.yongin.blog.blogbackend.service;

import com.yongin.blog.blogbackend.dao.BoardDao;
import com.yongin.blog.blogbackend.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 글쓰기
    public BoardDao save(BoardDao boardDao) {
        boardRepository.save(boardDao);
        return boardDao;
    }

    // 글 목록 불러오기
    public List<BoardDao> findAll() {
        List<BoardDao> boards = new ArrayList<>();
        boardRepository.findAll().forEach(e->boards.add(e));
        return boards;
    }

    // 글 삭제
    public void deleteById(Long id) {
        boardRepository.deleteById(id);

    }

    // 좋아요
    public Optional<BoardDao> updateById(Long id) {
        Optional<BoardDao> b = boardRepository.findById(id);
        if(b.isPresent()){
            int likes = b.get().getLikes();
            b.get().setLikes(likes+1);
            boardRepository.save(b.get());
        }
        return b;
    }

}
