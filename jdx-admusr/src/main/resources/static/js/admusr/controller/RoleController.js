'use strict';

/* Controllers */

var app = angular.module('app.admusr.controller');

/**
 * Definición del controlador de Roles
 */
app.controller(
				'RoleListController',
				[
						'$scope',
						'$rootScope',
						'RolesFactory',
						'FunctionalityFactory',
						'$location',
						'ModalFactory',
						function($scope, $rootScope, RolesFactory,
								FunctionalityFactory, $location, ModalFactory) {

							/*
							 * Inicialización de datos y carga inicial
							 */
							$scope.roles = RolesFactory.list();//Traemos todos los roles
							$scope.onEdit=undefined;
							$scope.sendController = undefined; 
							/*
							 * Definición de métodos
							 */

							$scope.$on('changeEntity', function(e,args) {
								
								$scope.sendController = {'controller':{'id':args}};
								$scope.functionalities = FunctionalityFactory.listByController($scope.sendController);
							});

							// callback for ng-change 'changeController':
							$scope.changeController = function(controller) {
								var sendController = {'controller':{'id':controller}};
								$scope.functionalities = FunctionalityFactory.listByController(sendController);
							};
							
							// callback for ng-click 'deleteRole'
							$scope.deleteRole = function(roleId) {
								// Display question modal, remove role on
								// succhess callback
								ModalFactory
										.question(
												"Remove",
												"Are you sure want to remove the role from the system?",
												function() {
													RolesFactory.remove({
														id : roleId
													}).$promise
															.then(
															// success
															function(value) {
																$scope.roles = RolesFactory.list();
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
								$scope.roles = RolesFactory.list();
								$scope.functionalities = FunctionalityFactory.listByController($scope.sendController);
								$scope.onEdit = undefined;
							};

							// callback for click on save role
							$scope.saveRole = function() {
								RolesFactory.update(this.role).$promise.then(
								// success
								function(value) {
									$scope.roles = RolesFactory.list();
									$scope.functionalities = FunctionalityFactory.listByController($scope.sendController);
									$scope.onEdit = undefined;
								});
							};

							// callback for ng-click 'createRole':
							$scope.createNewRole = function() {
								RolesFactory.create($scope.newRole).$promise
										.then(
										// success
										function(value) {
											$scope.newRole.rol = '';
											$scope.roles = RolesFactory.list();
											$scope.functionalities = FunctionalityFactory.listByController($scope.sendController);
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
		'RolesFactory', '$location',
		function($scope, $routeParams, RolesFactory, $location) {

			// callback for ng-click 'updateRole':
			$scope.updateRole = function() {
				RolesFactory.update($scope.role);
				$location.path('/role-list');
			};

			// callback for ng-click 'cancel':
			$scope.cancel = function() {
				$location.path('/role-list');
			};

			$scope.role = RolesFactory.get({
				id : $routeParams.id
			});
		} ]);
