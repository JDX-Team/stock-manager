package com.jdx.admusr.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdx.admusr.annotation.SecuredController;
import com.jdx.admusr.model.FunctionalityEntity;
import com.jdx.admusr.service.interfaces.FunctionalityService;
import com.jdx.common.web.GenericController;

/**
 * Functionality Rest controller
 *
 */
@RestController
@RequestMapping("/functionalities")
@SecuredController("Functionalities")
public class FunctionalityRestController extends GenericController<FunctionalityEntity> {
	 private static final Logger LOGGER = LoggerFactory.getLogger(FunctionalityRestController.class);
	 
	    /* func service */
	    @Autowired
	    FunctionalityService funcService;
	 
	    /**
	     * Returns list of functionalities 
	     * 
	     * @return list of functionalities
	     */
	    @RequestMapping(method = {RequestMethod.GET})
	    @ResponseBody
	    public List<FunctionalityEntity> getList() {
	        
	    	LOGGER.debug("Retrieving list of functionalities");
	        
	        return funcService.list();
	    }
	 
	    /**
	     * Proporciona las funcionalidades en formato json de la parte publica de la
	     * aplicación por controlador
	     * 
	     * @return los roles  con funcionalidades en formato json
	     */
	    @RequestMapping(method = {RequestMethod.POST}, value= "/search/controller")
	    @ResponseBody
	    public List<FunctionalityEntity> getListByController(@RequestBody FunctionalityEntity functionality) {
	        LOGGER.debug("Entramos en el controlador rest de la parte pública de la aplicación");
	        return funcService.list(functionality);
	    }
	    /**
	     * Obtain detail of one functionality
	     * 
	     * @param id
	     * @return functionality by id
	     */
	    @RequestMapping(value= "/{id}", 
	            method = {RequestMethod.GET})
	    @ResponseBody
	    public FunctionalityEntity getDetail(//@PathVariable("id") int id,
	    		@RequestBody FunctionalityEntity functionality) {
	    	LOGGER.debug("Retrieving functionality with id " + functionality.getId());
	        return funcService.read(functionality);
	    }
	 
	 
}
