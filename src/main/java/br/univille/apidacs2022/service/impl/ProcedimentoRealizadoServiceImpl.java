package br.univille.apidacs2022.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.apidacs2022.service.ProcedimentoRealizadoService;
import br.univille.coredacs2022.entity.ProcedimentoRealizado;
import br.univille.coredacs2022.repository.ProcedimentoRealizadoRepository;

@Service
public class ProcedimentoRealizadoServiceImpl implements ProcedimentoRealizadoService{

    @Autowired
    private ProcedimentoRealizadoRepository repository;

    @Override
    public List<ProcedimentoRealizado> getAll() {
        return repository.findAll();
    }

    @Override
    public ProcedimentoRealizado save(ProcedimentoRealizado procedimentoRealizado) {
        repository.save(procedimentoRealizado);
        return procedimentoRealizado;
    }

    @Override
    public ProcedimentoRealizado findById(long id) {
        Optional<ProcedimentoRealizado> procedimentoRealizado = repository.findById(id);
        if (procedimentoRealizado.isPresent()) {
            return procedimentoRealizado.get();
        }
        return null;
    }

    @Override
    public ProcedimentoRealizado delete(long id) {
        Optional<ProcedimentoRealizado> procedimentoRealizado = repository.findById(id);
        if (procedimentoRealizado.isPresent()) {
            var procRealizado = procedimentoRealizado.get();
            repository.deleteById(id);
            return procRealizado;
        }
        return null;
    }
    
}
