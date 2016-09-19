'use strict';

/* Factorys login*/
var factorys = angular.module('login.factory',['ngResource']);

factorys.factory('LoginFactory', function ($resource) {
	return null;
//    return $resource('./login', {
//    							}, {
//					    		 login:{
//					    			 method: 'POST',
//					    			 headers:{'Content-type':"application/x-www-form-urlencoded; charset=utf-8"},
//					    			 transformRequest: function(obj) {
//					    			        var str = [];
//					    			        for(var p in obj)
//					    			        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
//					    			        return str.join("&");
//					    			    },
//					    			 data: {username: this.username, password: this.password}
//					    		 },
//    	});
});

