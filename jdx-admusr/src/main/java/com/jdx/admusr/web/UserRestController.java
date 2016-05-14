package com.jdx.admusr.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jdx.admusr.annotation.SecuredController;
import com.jdx.admusr.model.UserEntity;
import com.jdx.admusr.service.interfaces.UserService;
import com.jdx.common.web.GenericController;

@RestController
@RequestMapping("/users")
@SecuredController("User")
public class UserRestController extends GenericController<UserEntity> {
	 private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);
	 
	    /* User service */
	    @Autowired
	    UserService userService;
	 
	    /**
	     * Incluye nuevos usuarios al listado de usuarios de la aplicacion
	     * 
	     * @return un usuario en formato json
	     */
	    @RequestMapping(method = {RequestMethod.POST})
	    @ResponseBody
	    @ResponseStatus(HttpStatus.CREATED)
	    public Object postUser(@RequestBody UserEntity user) {

	    	userService.add(user);
	        
	    	return generateMsg();
	        //return user; 
	    }
	 
	    /**
	     * Proporciona los usuarios en formato json de la parte publica de la
	     * aplicación
	     * 
	     * @return los usuarios en formato json
	     */
	    @RequestMapping(method = {RequestMethod.GET})
	    @ResponseBody
	    public List<UserEntity> getList() {
	        LOGGER.debug("Entramos en el controlador rest de la parte pública de la aplicación");
	        return userService.list();
	    }
	 
	    /**
	     * Visualiza el detalle de un usuario
	     * 
	     * @param id
	     * @return
	     */
	    @RequestMapping(value= "/{id}", 
	            method = {RequestMethod.GET})
	    @ResponseBody
	    public UserEntity getDetail(@PathVariable("id") int id) {
	        
	        return userService.read(id);
	    }
	 
	    /**
	     * Edita un usuario con los datos pasados por body
	     * 
	     * @param id
	     * @param account
	     * @return
	     */
	    @RequestMapping(value= "/{id}", 
	                    method = {RequestMethod.PUT})
	    @ResponseBody
	    @ResponseStatus(HttpStatus.OK)
	    public Object putUserUpdate(@PathVariable("id") int id, 
	                                   @RequestBody UserEntity user){
	    	userService.update(user);
	    	return generateMsg();
	    	//return user;
	    }
	 
	    /**
	     * Elimina un usuario pasado el id por parametro
	     * y cuyo metodo sea del tipo DELETE
	     * @param id
	     * @return
	     * @throws InterruptedException 
	     */
	    @RequestMapping(value= "/{id}", 
	            method = {RequestMethod.DELETE})
	    @ResponseBody
	    @ResponseStatus(HttpStatus.OK)
	    public Object deleteUser(@PathVariable("id") int id) throws InterruptedException{
	    	
	    	userService.delete(id);
	    	
	        return generateMsg();
	    }
	 
}
