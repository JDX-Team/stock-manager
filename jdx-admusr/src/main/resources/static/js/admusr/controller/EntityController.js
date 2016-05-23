'use strict';

/* Controllers */

var app = angular.module('app.admusr.controller');

/**
 * Definición del controlador de Roles
 */
app.controller('EntityController', ['$scope', '$rootScope','EntityFactory',
    function ($scope, $rootScope, EntityFactory) {
		
		/*
		 * Inicialización de datos y carga inicial
		 */
	EntityFactory.list().$promise.then(function(value){
		//Capturamos el listado y posicionamos el primero en el selected
		$scope.controllers = value;
		$scope.controllerSelected = value[0].id;
		
		//Lanzamos el evento onchange para que se carge el listado en base al controller seleccionado
		$rootScope.$broadcast('changeEntity', value[0].id);
		//Lanzamos el evento para el controller seleccionado
	});


	 	
}]);
