'use strict';
/* Factorys common*/
var module = angular.module('app.common.factory');

module.factory('ModalFactory', function($resource, $uibModal) {
	return {
		// Modal question type
		question : function(title, msg, success, cancel) {
			// Instantiate question type modal
			var modalInstance = $uibModal.open({
				templateUrl : 'modal/modalQuestion.html',
				windowTemplateUrl : 'modal/window.html',
				backdrop : false,
				controller : 'modalController',
				resolve : {
					title : function() {
						return title;
					},
					msg : function() // scope del modal
					{
						return msg;
					}
				}
			});

			// Declare the callback for success (user clicks on accept) or
			// cancel (user clicks on cancel)
			modalInstance.result.then(success, cancel);

		},
		// Modal error type
		error : function(msg) {
			// Instantiate error type modal
			var modalInstance = $uibModal.open({
				templateUrl : 'modal/modalError.html',
				windowTemplateUrl : 'modal/window.html',
				backdrop : false,
				controller : 'modalController',
				resolve : {
					title : function() {
						return "Error";
					},
					msg : function() // scope del modal
					{
						return msg;
					}
				}
			});

			// modalInstance.result.then(success, cancel);

		},
		success : function(msg) {
			// Instantiate error type modal
			var modalInstance = $uibModal.open({
				templateUrl : 'modal/modal.html',
				windowTemplateUrl : 'modal/window.html',
				backdrop : false,
				controller : 'modalController',
				resolve : {
					title : function() {
						return "Success";
					},
					msg : function() // scope del modal
					{
						return msg;
					}
				}
			});

		},
		info : function(msg, messsage) {
			// Instantiate error type modal
			var modalInstance = $uibModal.open({
				templateUrl : 'modal/modalInfo.html',
				windowTemplateUrl : 'modal/window.html',
				backdrop : false,
				controller : 'modalController',
				resolve : {
					title : function() {
						return "Info";
					},
					msg : function() // scope del modal
					{
						return messsage;
					}
				}
			});
		}
	};
});
