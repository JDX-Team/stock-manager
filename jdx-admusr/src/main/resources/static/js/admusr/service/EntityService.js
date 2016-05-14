'use strict';

/* Services admin user*/
var services = angular.module('admusr.service');

/****** Controllers ***************/
services.factory('EntityService', function ($resource) {
    return $resource('./controllers', {}, {
    		 list: 		{ method: 'GET', isArray: true },
    		 get: 		{ method: 'GET', url:'./controllers/:id', params: {id: '@id'} },
    	});
});

