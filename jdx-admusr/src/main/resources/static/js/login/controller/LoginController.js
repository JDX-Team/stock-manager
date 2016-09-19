'use strict';

/* Controllers */

var app = angular.module('login.controller', [ 'login.factory']);

app.controller('LoginController', [ '$scope', 'LoginFactory',
		function($scope, LoginFactory) {
			
//			$scope.clickLogin = function (){
//				LoginFactory.login({'username': this.username, 'password': this.password}).$promise.then(
//				// success
//				function(value) {
//					$state.go('index');
//				});
//			};

		} ]);
