package controllers;

import play.mvc.Controller;
import play.mvc.*;
import security.Seguranca;

import java.util.*;

@With(Seguranca.class)
public class Application extends Controller {

    public static void index() {
        render();
    }

}