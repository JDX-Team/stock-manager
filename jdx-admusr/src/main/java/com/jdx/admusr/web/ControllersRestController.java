package com.jdx.admusr.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdx.admusr.annotation.SecuredController;
import com.jdx.admusr.model.ControllerEntity;
import com.jdx.admusr.service.interfaces.ControllerService;
import com.jdx.common.web.GenericController;

/**
 * Controller Rest controller
 *
 */
@RestController
@RequestMapping("/controllers")
@SecuredController("Controllers")
public class ControllersRestController extends GenericController<ControllerEntity>{
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllersRestController.class);

	/* func service */
	@Autowired
	ControllerService controllerService;

	/**
	 * Returns list of controllers
	 * 
	 * @return list of controllers
	 */
	@RequestMapping(method = { RequestMethod.GET })
	@ResponseBody
	public List<ControllerEntity> getList() {

		LOGGER.debug("Retrieving list of controllers");

		return controllerService.list();
	}

	/**
	 * Obtain detail of one controller
	 * 
	 * @param id
	 * @return controller by id
	 */
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public ControllerEntity getDetail(@PathVariable("id") int id) {

		LOGGER.debug("Retrieving controller with id " + id);
		ControllerEntity entity = new ControllerEntity();
		entity.setId(id);
		return controllerService.read(entity);
	}
	
}
