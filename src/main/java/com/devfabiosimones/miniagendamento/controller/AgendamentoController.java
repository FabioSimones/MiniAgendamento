package com.devfabiosimones.miniagendamento.controller;

import com.devfabiosimones.miniagendamento.dto.AgendamentoCreateRequest;
import com.devfabiosimones.miniagendamento.dto.AgendamentoResponse;
import com.devfabiosimones.miniagendamento.dto.AgendamentoUpdateRequest;
import com.devfabiosimones.miniagendamento.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public AgendamentoResponse criar(@Valid @RequestBody AgendamentoCreateRequest request){
        return agendamentoService.criar(request);
    }

    @PutMapping("/{id}")
    public AgendamentoResponse atualizar(@PathVariable Long id, @Valid @RequestBody AgendamentoUpdateRequest request){
        return agendamentoService.atualizar(id, request);
    }

    @PutMapping("/{id}/cancelar")
    public AgendamentoResponse cancelar(@PathVariable Long id){
        return agendamentoService.cancelar(id);
    }

    @PutMapping("/{id}/concluir")
    public AgendamentoResponse concluir(@PathVariable Long id){
        return agendamentoService.concluir(id);
    }

    @GetMapping("/{id}")
    public AgendamentoResponse buscarPorId(@PathVariable Long id){
        return agendamentoService.buscarPorId(id);
    }
}
