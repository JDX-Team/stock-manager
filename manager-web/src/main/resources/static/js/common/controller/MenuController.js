'use strict';
var module = angular.module('app.common.controller');

// Controller for menu
module.controller('MenuController', [ '$scope', '$location', 'MenuService',
		function($scope, $location, MenuService) {

			$scope.items = MenuService.load();

			/**
			 * Méthod to paint de selected index
			 */
			$scope.isActive = function(viewLocation) {
				return viewLocation === $location.path();
			};

		} ]);
