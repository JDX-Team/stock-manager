'use strict';
/* Factorys common*/
var module = angular.module('app.common.factory'); //, ['ngResource', 'ui.bootstrap' ]

/****** Menu***************/
module.factory('MenuFactory', function ($resource) {
	return $resource('./static/json/menu.json', {}, {
    		 load:{ method: 'GET', isArray: true }
    	});
});
