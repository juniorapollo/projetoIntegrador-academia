package br.com.pilates.academiaPilates.service;

import br.com.pilates.academiaPilates.models.Cliente;
import br.com.pilates.academiaPilates.repository.TurmaRepository;
import br.com.pilates.academiaPilates.models.Turma;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class TurmaService {

    @Autowired
    TurmaRepository repo;

    private Turma turma;

    public Turma turmaPorId(Long id) {
        try {
            return repo.findOne(id);
        } catch (Exception e) {
            System.out.println("Catch TurmaService:26" + e);
            return null;
        }
    }

    public List<Turma> listaTurma(boolean ativo, boolean inativo) {
        try {
            if (ativo && inativo) {
                 System.out.println("Listar Turma Ativa e Inativa");
                return repo.findAll(new Sort(Sort.Direction.ASC, "nome"));
               
            } else if ((!ativo) && inativo) {
                return repo.findByAtivoFalseOrderByNomeAsc();
            } else {
                 System.out.println("Ativo True ");
                 return repo.findByAtivoTrueOrderByNomeAsc();
               
            }
        } catch (Exception e) {
            System.out.println("Catch TurmaService:42" + e);
            return null;
        }
    }

    public Turma salvarTurma(Turma turma, List<Cliente> clientes) {
        if (existeClientes(turma)) {
            repo.save(turma);
            System.out.println("Clientes : " + turma.getClientes().toString());
            return turma;
        } else {
            turma.setClientes(clientes);
            repo.save(turma);
            return turma;
        }
    }

    public Turma inativarTurma(Turma turma) {
        try {
            if (turma.isAtivo()) {
                turma.setAtivo(false);
                repo.save(turma);
                System.out.println("AAAASDADASDASDADS");
                return turma;
            }

        } catch (Exception e) {
            System.out.println("Catch Inativar Turma");
            return turma;
        }

        return turma;

    }

    public Turma ativarTurma(Turma turma) {
        if (!(turma.isAtivo())) {
            turma.setAtivo(true);
        }
        return turma;
    }

    public boolean existeClientes(Turma turma) {
        return !turma.getClientes().isEmpty();
    }

    public Turma getTurma() {
        return new Turma();
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

}
