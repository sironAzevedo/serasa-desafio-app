package com.serasa.desafio.controller;

import com.serasa.desafio.model.dto.PessoaRequestDTO;
import com.serasa.desafio.model.dto.PessoaResponseDTO;
import com.serasa.desafio.service.IPessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pessoa")
public class PessoaController {

    private final IPessoaService service;

    @PostMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(
            summary = "Realiza o cadastro da pessoa",
            tags = "Pessoa"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void inserir(@Valid @RequestBody PessoaRequestDTO dto) {
        service.inserir(dto);
    }

    @ResponseBody
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(
            summary = "Realiza a consulta de todas as pessoas",
            tags = "Pessoa"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "204", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<PessoaResponseDTO> findAll() {
        return service.findAll();
    }

    @ResponseBody
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(
            summary = "Realiza a consulta de pessoa por código",
            tags = "Pessoa"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "204", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PessoaResponseDTO findById(@PathVariable(name = "id") Long codigo) {
        return service.findById(codigo);
    }
}
