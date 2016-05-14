'use strict';
var module = angular.module('app.common.controller');

// Controller for modal
module.controller('modalController', [ '$scope', '$uibModalInstance', 'title',
		'msg', function($scope, $uibModalInstance, title, msg) {
			$scope.title = title;
			$scope.msg = msg;

			// Close callback, close modal
			$scope.close = function() {
				$uibModalInstance.dismiss('close');
			};

			// Accept callback, close modal and accept operation
			$scope.accept = function() {
				$uibModalInstance.close();
			};

			// Cancel callback, close modal and dismiss operation
			$scope.cancel = function() {
				$uibModalInstance.dismiss('close');
			};
		} ]);