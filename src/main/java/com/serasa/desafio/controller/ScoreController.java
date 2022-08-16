package com.serasa.desafio.controller;

import com.serasa.desafio.model.dto.ScoreRequestDTO;
import com.serasa.desafio.service.IScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/score")
public class ScoreController {

    private final IScoreService service;

    @PostMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public void inserir(@Valid @RequestBody ScoreRequestDTO dto) {
        service.inserir(dto);
    }
}
