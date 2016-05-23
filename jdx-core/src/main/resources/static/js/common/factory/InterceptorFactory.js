var module = angular.module('app.common.factory');

module.factory('httpErrorsInterceptor', [
		'$q',
		'$rootScope',
		'$injector',
		function($q, $rootScope, $injector) {
			return {
				'responseError' : function(response) {

					switch (response.status) {
					case (403):
					case (401):
						// redirect to login
						window.location.replace("./public/login");
						break;
					case (500):
					case (422):
						if (response.data != undefined
								&& response.data.errorMsg != undefined) {
							console.error("Error: " + response.data.errorMsg);
							var modalFactory = $injector.get('ModalFactory');
							modalFactory.error(response.data.errorMsg);
						}
						break;

					default:
						break;
					}

					// Always reject (or resolve) the deferred you're given
					return $q.reject(response);
				},
				'response' : function(response) {
					switch (response.status) {
					case 200:
						break;
					case 201:
						var modalFactory = $injector.get('ModalFactory');
						modalFactory.success(response.data.msg);
						break;
					default:
						break;
					}
					// Always reject (or resolve) the deferred you're given
					return response;
				}
			};

		} ]);