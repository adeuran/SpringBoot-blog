package com.yongin.blog.blogbackend.controller;

import com.yongin.blog.blogbackend.dao.BoardDao;
import com.yongin.blog.blogbackend.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public List<BoardDao> createList(Model model) {
        List<BoardDao> boardDaos = boardService.findAll();
        return boardDaos;
    }

    @PostMapping("/write")
    public ResponseEntity save(HttpServletRequest request, BoardDao board) {
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd");
        board.setIssued(new Date());
        return ResponseEntity.created(null).body(boardService.save(board));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Map<String, HttpStatus> status = new HashMap<>();
        try {
            boardService.deleteById(id);
            status.put("status",HttpStatus.OK);
            return ResponseEntity.ok().body(status);
        } catch (EmptyResultDataAccessException e) {
            status.put("status",HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(status);
        }
    }

    @GetMapping("/like/{id}")
    public ResponseEntity<Object> like(@PathVariable("id") Long id) {
        ResponseEntity<Object> responseEntity;
        try{
            responseEntity = new ResponseEntity<>(boardService.updateById(id).get(), HttpStatus.OK);}
        catch (NoSuchElementException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("status",HttpStatus.NOT_FOUND);
            responseEntity = new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
