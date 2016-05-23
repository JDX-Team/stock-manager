'use strict';
var module = angular.module('app.common.controller');

// Controller for menu
module.controller('MenuController', [ '$scope', '$location', 'MenuFactory',
		function($scope, $location, MenuFactory) {

			$scope.items = MenuFactory.load();

			/**
			 * Méthod to paint de selected index
			 */
			$scope.isActive = function(viewLocation) {
				return viewLocation === $location.path();
			};

		} ]);
