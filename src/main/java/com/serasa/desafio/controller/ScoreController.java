package com.serasa.desafio.controller;

import com.serasa.desafio.model.dto.ScoreRequestDTO;
import com.serasa.desafio.service.IScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Realiza o cadastro de score",
            tags = "Score"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void inserir(@Valid @RequestBody ScoreRequestDTO dto) {
        service.inserir(dto);
    }
}
