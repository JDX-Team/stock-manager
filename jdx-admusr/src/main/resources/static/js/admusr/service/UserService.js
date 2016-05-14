'use strict';

/* Services admin user*/
var services = angular.module('admusr.service', ['ngResource']);

/****** Users ***************/
services.factory('UsersService', function ($resource) {
    return $resource('./users', {}, {
    		 list: 		{ method: 'GET', isArray: true },
    		 create: 	{ method: 'POST' },
    		 get: 		{ method: 'GET', url:'./users/:id', params: {id: '@id'} },
    		 update: 	{ method: 'PUT', url:'./users/:id', params: {id: '@id'} },
    		 remove: 	{ method: 'DELETE',url:'./users/:id', params: {id: '@id'} }
    	});
});

