'use strict';

var module = angular.module('app.common.service');

module.factory('translateLoader', function ($http, $q) {
    // return loaderFn
    return function (options) {
    	var defered = $q.defer();
    	var promise = defered.promise;
    	
		$http.get(options.url).success(function(data) {
			defered.resolve(data);
		}).error(function(err) {
			defered.reject(err);
		});
        return promise
    };
});

