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

import br.univille.apidacs2022.service.ConsultaService;
import br.univille.coredacs2022.entity.Consulta;

@RestController
@RequestMapping("/api/v1/consultas")
public class ConsultaControllerAPI {
    
    @Autowired
    private ConsultaService service;

    @GetMapping
    public ResponseEntity<List<Consulta>> getAll() {
        var listaConsultas = service.getAll();
        return new ResponseEntity<List<Consulta>>(listaConsultas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Consulta> insertPaciente(@RequestBody Consulta consulta) {
        if (consulta.getId() == 0) {
            service.save(consulta);
            return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);
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
    public ResponseEntity<Consulta> getById(@PathVariable("id") long id){

        var consulta = service.findById(id);
        return new ResponseEntity<Consulta>
                (consulta,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> update(@PathVariable("id") long id, @RequestBody Consulta consulta) {
        var consultaAntiga = service.findById(id);

        if(consultaAntiga == null){
            return ResponseEntity.notFound().build();
        }

        consultaAntiga.setData(consulta.getData());
        consultaAntiga.setStatus(consulta.getStatus());
        consultaAntiga.setPaciente(consulta.getPaciente());
        consultaAntiga.setMedicoResponsavel(consulta.getMedicoResponsavel());
        consultaAntiga.setListaProcedimentos(consulta.getListaProcedimentos());

        service.save(consultaAntiga);

        return new ResponseEntity<Consulta>(consultaAntiga,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Consulta> delete(@PathVariable("id") long id) {
        var consultaAntiga = service.findById(id);

        if(consultaAntiga == null){
            return ResponseEntity.notFound().build();
        }
        service.delete(consultaAntiga.getId());

        return new ResponseEntity<Consulta>(consultaAntiga,HttpStatus.OK);
    }
    
}