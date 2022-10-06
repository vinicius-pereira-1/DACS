package br.univille.apidacs2022.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.apidacs2022.service.ProcedimentoRealizadoService;
import br.univille.coredacs2022.entity.ProcedimentoRealizado;

@RestController
@RequestMapping("/api/v1/procedimentosRealizados")
public class ProcedimentoRealizadoControllerAPI {
    
    @Autowired
    private ProcedimentoRealizadoService service;

    @GetMapping
    public ResponseEntity<List<ProcedimentoRealizado>> getAll() {
        var listaProcedimentosRealizados = service.getAll();
        return new ResponseEntity<List<ProcedimentoRealizado>>(listaProcedimentosRealizados, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProcedimentoRealizado> insertPaciente(@RequestBody ProcedimentoRealizado procedimentoRealizado) {
        if (procedimentoRealizado.getId() == 0) {
            service.save(procedimentoRealizado);
            return new ResponseEntity<ProcedimentoRealizado>(procedimentoRealizado, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    /*
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Procedimento>> getByNome(@PathVariable("nome") String nome) {
        var listaProcedimentos = service.getByName(nome);
        
        return new ResponseEntity<List<Procedimento>>(listaProcedimentos, HttpStatus.OK);
    }
     */
    
    @GetMapping("/{id}")
    public ResponseEntity<ProcedimentoRealizado> getById(@PathVariable("id") long id){

        var procedimentoRealizado = service.findById(id);
        return new ResponseEntity<ProcedimentoRealizado>
                (procedimentoRealizado,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedimentoRealizado> update(@PathVariable("id") long id, @RequestBody ProcedimentoRealizado procedimentoRealizado) {
        var procedimentoRealizadoAntigo = service.findById(id);

        if(procedimentoRealizadoAntigo == null){
            return ResponseEntity.notFound().build();
        }

        procedimentoRealizadoAntigo.setDescricao(procedimentoRealizadoAntigo.getDescricao());
        procedimentoRealizadoAntigo.setValor(procedimentoRealizadoAntigo.getValor());
        procedimentoRealizadoAntigo.setProcedimento(procedimentoRealizadoAntigo.getProcedimento());

        service.save(procedimentoRealizadoAntigo);

        return new ResponseEntity<ProcedimentoRealizado>(procedimentoRealizadoAntigo,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProcedimentoRealizado> delete(@PathVariable("id") long id) {
        var procedimentoRealizadoAntigo = service.findById(id);

        if(procedimentoRealizadoAntigo == null){
            return ResponseEntity.notFound().build();
        }
        service.delete(procedimentoRealizadoAntigo.getId());

        return new ResponseEntity<ProcedimentoRealizado>(procedimentoRealizadoAntigo,HttpStatus.OK);
    }
    
}