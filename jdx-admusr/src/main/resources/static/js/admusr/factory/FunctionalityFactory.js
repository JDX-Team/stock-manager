'use strict';

/* Facorys admin role*/
var factorys = angular.module('app.admusr.factory');

/****** Functionality ***************/
factorys.factory('FunctionalityFactory', function ($resource) {
    return $resource('./functionalities', {}, {
        list: 	{ method: 'GET', isArray: true },
		listByController: 	{ method: 'POST', url:'./functionalities/search/controller', isArray: true }
    })
});