'use strict';

/* Services admin role*/
var services = angular.module('admusr.service');

/****** Functionality ***************/
services.factory('FunctionalityService', function ($resource) {
    return $resource('./functionalities', {}, {
        list: 	{ method: 'GET', isArray: true },
		listByController: 	{ method: 'POST', url:'./functionalities/search/controller', isArray: true }
    })
});