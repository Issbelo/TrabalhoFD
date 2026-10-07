package Jobs;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import models.Pessoa;

@OnApplicationStart
public class Log extends Job {

    public void doJob() {
        if (Pessoa.count() == 0) {
            Pessoa p = new Pessoa();
            p.nome = "Administrador";
            p.email = "admin@teste.com";
            p.login = "admin";
            p.senha = "123456";
            p.save();
        }
    }
}