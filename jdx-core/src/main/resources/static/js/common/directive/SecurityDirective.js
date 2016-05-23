'use strict';

var module = angular.module('app.common.directive');

//secure-action directive, check if current user have rights for provided operation, else hide the element
module.directive('secureActionShow', function() {

	return {
		link : function ( $scope, $element, $attributes) {
			//Check if secure-action value exists in userRights array
			if($.inArray($attributes.secureActionShow, userRights)<0) {
				$element.hide();
			}else{
				$element.show();
			}
		},
		restrict : "A"
	};
});
//secure-action directive, check if current user have rights for provided operation, else disable the element
module.directive('secureActionEnable', function() {

	return {
		link : function ( $scope, $element, $attributes) {
			//Check if secure-action value exists in userRights array
			if($.inArray($attributes.secureActionEnable, userRights)<0) {
				$element.attr('disabled',true);
			}else{
				$element.attr('disabled',false);
			}
		},
		restrict : "A"
	};
});