'use strict';

/* Services admin role*/
var services = angular.module('app.admusr.factory');

///****** Roles ***************/
services.factory('RolesFactory', function ($resource) {
    return $resource('./roles', {}, {
    	list: 	{ method: 'GET', isArray: true },
        create: { method: 'POST' },
    	show: 	{ method: 'GET' , 	url:'./roles/:id'},
        update: { method: 'PUT' , 	url:'./roles/:id', params: {id: '@id'} },
        remove: { method: 'DELETE', url:'./roles/:id', params: {id: '@id'} }
    })
});

