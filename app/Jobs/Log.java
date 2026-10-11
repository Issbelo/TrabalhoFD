package Jobs;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import models.Pessoa;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import models.Perfil;

@OnApplicationStart
public class Log extends Job {

    public void doJob() {
        if (Pessoa.count() == 0) {
            Pessoa p = new Pessoa();
            p.login = "admin";
            p.senha = "123456";
            p.perfil = Perfil.ADMIN;
            p.save();
            
            Pessoa p2 = new Pessoa();
            p2.login = "teste";
            p2.senha = "123456";
            p2.perfil = Perfil.FORNECEDOR;
            p2.save();
        }
    }
}