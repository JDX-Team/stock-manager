'use strict';
/* Services common*/
var module = angular.module('app.common.service'); //, ['ngResource', 'ui.bootstrap' ]

/****** Menu***************/
module.factory('MenuService', function ($resource) {
	return $resource('./static/json/menu.json', {}, {
    		 load:{ method: 'GET', isArray: true }
    	});
});
