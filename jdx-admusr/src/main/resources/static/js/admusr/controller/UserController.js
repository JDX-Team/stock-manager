'use strict';

/* Controllers */

var app = angular.module('admusr.user',['admusr.service']);

app.controller('UserListController', ['$scope', 'UsersService','RolesService', '$location','ModalService',
    function ($scope, UsersService, RolesService, $location, ModalService) {

	 	$scope.users = UsersService.list();
	 	$scope.roles = RolesService.list();
	 	$scope.onEdit=undefined;

        // callback for ng-click 'deleteUser'
        $scope.deleteUser = function (userId) {
        	//Display question modal, remove user on succhess callback
        	ModalService.question("Remove","Are you sure want to remove the user from the system?",function(){
        		UsersService.remove({ id: userId }).$promise.then(
            	    //success
            	    function( value ){
            	    	$scope.users = UsersService.list();
                });
        	},function(){});
        };
        
        // callback for ng-click 'editUser':
        $scope.editUser = function () {
        	this.user.editable=true;
        	$scope.onEdit=this.user.id;
     
        };
        
        // callback for ng-click 'editUser':
        $scope.cancelEdit = function () {
        	this.user.editable=false;
        	$scope.users = UsersService.list();
        	$scope.onEdit=undefined;
        };
        
        //callback for click on save user
        $scope.saveUser = function () {
        	UsersService.update(this.user).$promise.then(
            	    //success
            	    function( value ){
            	    	$scope.users = UsersService.list();
            	    	$scope.onEdit=undefined;
                });
        };

		// callback for ng-click 'createUser':
        $scope.createNewUser = function () {
        	UsersService.create($scope.newUser).$promise.then(
            	    //success
            	    function( value ){
            	    	$scope.newUser.user = '';
            	    	$scope.users = UsersService.list();
            	    	$scope.onEdit=undefined;
                });
            
        }		
		
		// callback for ng-click 'createUser':
		$scope.isChecked = function (user,roleId) {
			for(var i=0; i< user.roles.length ; i++)
			{
				if(user.roles[i].id == roleId)
					return true;
			}
		   return false;
		};
    }]);
//
app.controller('UserDetailController', ['$scope', '$routeParams', 'UsersService', '$location',
    function ($scope, $routeParams, UsersService, $location) {

        // callback for ng-click 'updateUser':
        $scope.updateUser = function () {
        	UsersService.update($scope.user);
            $location.path('/user-list');
        };

        // callback for ng-click 'cancel':
        $scope.cancel = function () {
            $location.path('/user-list');
        };

        $scope.user = UsersService.get({id: $routeParams.id});
    }]);




