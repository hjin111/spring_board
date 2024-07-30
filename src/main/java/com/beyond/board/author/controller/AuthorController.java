package com.beyond.board.author.controller;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateDto;
import com.beyond.board.author.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

//    @PostMapping("/author/create")
//    public String authorCreate(@RequestBody AuthorSaveReqDto dto){
//        Author author = authorService.authorCreate(dto);
//        return author.getId() + "번 회원 가입완료";
//    }

    @GetMapping("/author/register")
    public String authorCreateScreen(){
        return "author/author_register";
    }

    @PostMapping("/author/register")
    public String authorCreate( @ModelAttribute AuthorSaveReqDto dto){
        authorService.authorCreate(dto);
        return "redirect:/";
    }

    @GetMapping("/author/list")
    public String authorList(Model model){
        List<AuthorListResDto> authorListResDtos = authorService.authorList();
        model.addAttribute("authorList" , authorListResDtos );
        return "author/author_list";
    }

    @GetMapping("/author/detail/{id}")
    public String authorDetail(@PathVariable Long id, Model model){
//        log.info("get요청이고, parameter는 " + id);
//        log.info("method명 : authorDetail");
        AuthorDetailDto authorDetailDto = authorService.authorDetail(id);
        model.addAttribute("author", authorDetailDto);
        return "author/author_detail";
    }

    @GetMapping("/author/delete/{id}")
    public String authorDelete(@PathVariable Long id){
        authorService.delete(id);
        return "redirect:/author/list";
    }

    @PostMapping("/author/update/{id}")
    public String authorUpdate(@PathVariable Long id, @ModelAttribute AuthorUpdateDto dto ){
        authorService.update(id, dto);
        return "redirect:/author/detail/" + id;
    }

}
