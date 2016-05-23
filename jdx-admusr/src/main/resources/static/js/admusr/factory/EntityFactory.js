'use strict';

/* Factorys admin user*/
var factorys = angular.module('app.admusr.factory');


factorys.factory('EntityFactory', function ($resource) {
    return $resource('./controllers', {}, {
    		 list: 		{ method: 'GET', isArray: true },
    		 get: 		{ method: 'GET', url:'./controllers/:id', params: {id: '@id'} },
    	});
});

