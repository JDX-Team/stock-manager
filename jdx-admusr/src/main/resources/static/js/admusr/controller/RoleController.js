'use strict';

/* Controllers */

var app = angular.module('admusr.role', [ 'admusr.entity.select',
		'admusr.service' ]);

/**
 * Definición del controlador de Roles
 */
app
		.controller(
				'RoleListController',
				[
						'$scope',
						'$rootScope',
						'RolesService',
						'FunctionalityService',
						'$location',
						'ModalService',
						function($scope, $rootScope, RolesService,
								FunctionalityService, $location, ModalService) {

							/*
							 * Inicialización de datos y carga inicial
							 */
							$scope.roles = RolesService.list();//Traemos todos los roles
							$scope.onEdit=undefined;
							$scope.sendController = undefined; 
							/*
							 * Definición de métodos
							 */

							$scope.$on('changeEntity', function(e,args) {
								
								$scope.sendController = {'controller':{'id':args}};
								$scope.functionalities = FunctionalityService.listByController($scope.sendController);
							});

							// callback for ng-change 'changeController':
							$scope.changeController = function(controller) {
								var sendController = {'controller':{'id':controller}};
								$scope.functionalities = FunctionalityService.listByController(sendController);
							};
							
							// callback for ng-click 'deleteRole'
							$scope.deleteRole = function(roleId) {
								// Display question modal, remove role on
								// succhess callback
								ModalService
										.question(
												"Remove",
												"Are you sure want to remove the role from the system?",
												function() {
													RolesService.remove({
														id : roleId
													}).$promise
															.then(
															// success
															function(value) {
																$scope.roles = RolesService.list();
															});
												}, function() {
												});
							};

							// callback for ng-click 'editRole':
							$scope.editRole = function() {
								this.role.editable = true;
								$scope.onEdit = this.role.id;
							};

							// callback for ng-click 'editRole':
							$scope.cancelEdit = function() {
								this.role.editable = false;
								$scope.roles = RolesService.list();
								$scope.functionalities = FunctionalityService.listByController($scope.sendController);
								$scope.onEdit = undefined;
							};

							// callback for click on save role
							$scope.saveRole = function() {
								RolesService.update(this.role).$promise.then(
								// success
								function(value) {
									$scope.roles = RolesService.list();
									$scope.functionalities = FunctionalityService.listByController($scope.sendController);
									$scope.onEdit = undefined;
								});
							};

							// callback for ng-click 'createRole':
							$scope.createNewRole = function() {
								RolesService.create($scope.newRole).$promise
										.then(
										// success
										function(value) {
											$scope.newRole.rol = '';
											$scope.roles = RolesService.list();
											$scope.functionalities = FunctionalityService.listByController($scope.sendController);
											$scope.onEdit = undefined;
										});

							}

							// callback for ng-click 'createRole':
							$scope.isChecked = function(role, functionalityId) {

								for (var i = 0; i < role.functionalities.length; i++) {
									if (role.functionalities[i].id == functionalityId)
										return true;
								}
								return false;
							};
						} ]);
//
app.controller('RoleDetailController', [ '$scope', '$routeParams',
		'RolesService', '$location',
		function($scope, $routeParams, RolesService, $location) {

			// callback for ng-click 'updateRole':
			$scope.updateRole = function() {
				RolesService.update($scope.role);
				$location.path('/role-list');
			};

			// callback for ng-click 'cancel':
			$scope.cancel = function() {
				$location.path('/role-list');
			};

			$scope.role = RolesService.get({
				id : $routeParams.id
			});
		} ]);
