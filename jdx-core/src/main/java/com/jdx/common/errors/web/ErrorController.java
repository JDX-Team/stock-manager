package com.jdx.common.errors.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller que trata lo endpoints a los que la arquitectura redirige cuando
 * se han producido errores no controlados.
 */
@Controller
public class ErrorController {

    /**
     * Trata los errores no controlados de peticiones que no esperaban
     * respuestas json ni xml.
     * 
     * @param request
     *            objeto {@link HttpServletRequest} de la peticion que contiene
     *            un atributo "exception" con la excepcion causante del error.
     * @param model
     *            objeto {@link Model} para rellenar la respuesta.
     * @return nombre de la vista/plantilla que renderiza el mensaje de error.
     */
    @RequestMapping(value = "/error/default")
    public String handleExceptionDefault(HttpServletRequest request, Model model) {
        // TODO: realizar el tratamiento deseado (normalmente devolver un
        // mensaje de error generico)
        return null;
    }

    /**
     * Trata los errores no controlados de peticiones que esperaban respuestas
     * json.
     * 
     * @param request
     *            objeto {@link HttpServletRequest} de la peticion que contiene
     *            un atributo "exception" con la excepcion causante del error.
     * @return objeto de respuesta con el mensaje de error.
     */
    @RequestMapping(value = "/error/json")
    @ResponseBody
    public Object handleExceptionJson(HttpServletRequest request) {
        // TODO: realizar el tratamiento deseado (normalmente devolver un json
        // de error generico)
        return null;
    }

    /**
     * Trata los errores no controlados de peticiones que esperaban respuestas
     * xml.
     * 
     * @param request
     *            objeto {@link HttpServletRequest} de la peticion que contiene
     *            un atributo "exception" con la excepcion causante del error.
     * @return objeto de respuesta con el mensaje de error.
     */
    @RequestMapping(value = "/error/xml")
    @ResponseBody
    public Object handleExceptionXml(HttpServletRequest request) {
        // TODO: realizar el tratamiento deseado (normalmente devolver un xml de
        // error generico)
        return null;
    }
}
