package com.devfabiosimones.miniagendamento.service;

import com.devfabiosimones.miniagendamento.dto.AgendamentoCreateRequest;
import com.devfabiosimones.miniagendamento.dto.AgendamentoResponse;
import com.devfabiosimones.miniagendamento.dto.AgendamentoUpdateRequest;
import com.devfabiosimones.miniagendamento.mapper.AgendamentoMapper;
import com.devfabiosimones.miniagendamento.model.Agendamento;
import com.devfabiosimones.miniagendamento.model.StatusAgendamento;
import com.devfabiosimones.miniagendamento.repository.AgendamentoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AgendamentoResponse criar(@Valid AgendamentoCreateRequest req){

        validarIntervalo(req.dataInicio(), req.dataFim());
        checkConflito(req.usuario(), req.dataInicio(), req.dataFim(), null);

        Agendamento entity = AgendamentoMapper.toEntity(req);
        entity = repository.save(entity);
        return AgendamentoMapper.toResponse(entity);
    }

    @Transactional
    public AgendamentoResponse atualizar(Long id, @Valid AgendamentoUpdateRequest req){
        Agendamento entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com id: " + id));
        AgendamentoMapper.merge(entity, req);
        validarIntervalo(req.dataInicio(), req.dataFim());
        checkConflito(entity.getUsuario(), req.dataInicio(), req.dataFim(), entity.getId());

        entity = repository.save(entity);
        return AgendamentoMapper.toResponse(entity);
    }

    @Transactional
    public AgendamentoResponse cancelar(Long id){
        Agendamento entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com id: " + id));
        entity.setStatus(StatusAgendamento.CANCELADO);
        entity = repository.save(entity);
        return AgendamentoMapper.toResponse(entity);
    }

    @Transactional
    public AgendamentoResponse concluir(Long id){
        Agendamento entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com id: " + id));
        entity.setStatus(com.devfabiosimones.miniagendamento.model.StatusAgendamento.CONCLUIDO);
        entity = repository.save(entity);
        return AgendamentoMapper.toResponse(entity);
    }

    @Transactional(readOnly = true)
    public AgendamentoResponse buscarPorId(Long id){
        Agendamento entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com id: " + id));
        return AgendamentoMapper.toResponse(entity);
    }

    private void checkConflito(String usuario, LocalDateTime inicio, LocalDateTime fim, Long id) {
        if (repository.existsConflito(usuario, inicio, fim, id)) {
            throw new IllegalStateException("Conflito de agendamento: já existe um agendamento para o usuário no intervalo informado.");
        }
    }

    private void validarIntervalo(LocalDateTime inicio,LocalDateTime fim) {
        if (inicio == null || fim == null || !inicio.isBefore(fim)) {
            throw new IllegalArgumentException("Intervalo de datas inválido: dataIncio deve ser anterior a dataFim.");
        }
    }
}
