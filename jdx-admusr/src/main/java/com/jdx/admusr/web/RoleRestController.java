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
import com.jdx.admusr.model.RoleEntity;
import com.jdx.admusr.service.interfaces.RoleService;
import com.jdx.common.web.GenericController;

@RestController
@RequestMapping("/roles")
@SecuredController("Roles")
public class RoleRestController extends GenericController<RoleEntity> {
	 private static final Logger LOGGER = LoggerFactory.getLogger(RoleRestController.class);
	 
	    /* Role service */
	    @Autowired
	    RoleService roleService;
	 
	    /**
	     * Incluye nuevos usuarios al listado de usuarios de la aplicacion
	     * 
	     * @return un usuario en formato json
	     */
	    @RequestMapping(method = {RequestMethod.POST})
	    @ResponseBody
	    @ResponseStatus(HttpStatus.CREATED)
	    public Object postRole(@RequestBody RoleEntity role) {
	 
	    	roleService.add(role);
	        
	    	return generateMsg();
	        //return role; 
	    }
	 
	    /**
	     * Proporciona los usuarios en formato json de la parte publica de la
	     * aplicación
	     * 
	     * @return los roles  con funcionalidades en formato json
	     */
	    @RequestMapping(method = {RequestMethod.GET})
	    @ResponseBody
	    public List<RoleEntity> getList() {
	        LOGGER.debug("Entramos en el controlador rest de la parte pública de la aplicación");
	        return roleService.list();
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
	    public RoleEntity getDetail(@PathVariable("id") int id) {
	        
	        return roleService.read(id);
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
	    @ResponseStatus(HttpStatus.CREATED)
	    public Object putRoleUpdate(@PathVariable("id") int id, 
	                                   @RequestBody RoleEntity role){
	    	roleService.update(role);
	    	return generateMsg();
	    //	return role;
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
	    @ResponseStatus(HttpStatus.CREATED)
	    public Object deleteRole(@PathVariable("id") int id) throws InterruptedException{
	    	
	    	roleService.delete(id);
	    	
	        return generateMsg();
	    }
	 
}
