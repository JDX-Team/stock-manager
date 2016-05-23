'use strict';

/* Controllers */

var app = angular.module('app.admusr.controller');

app.controller('UserListController', ['$scope', 'UsersFactory','RolesFactory', '$location','ModalFactory',
    function ($scope, UsersFactory, RolesFactory, $location, ModalFactory) {

	 	$scope.users = UsersFactory.list();
	 	$scope.roles = RolesFactory.list();
	 	$scope.onEdit=undefined;

        // callback for ng-click 'deleteUser'
        $scope.deleteUser = function (userId) {
        	//Display question modal, remove user on succhess callback
        	ModalFactory.question("Remove","Are you sure want to remove the user from the system?",function(){
        		UsersFactory.remove({ id: userId }).$promise.then(
            	    //success
            	    function( value ){
            	    	$scope.users = UsersFactory.list();
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
        	$scope.users = UsersFactory.list();
        	$scope.onEdit=undefined;
        };
        
        //callback for click on save user
        $scope.saveUser = function () {
        	UsersFactory.update(this.user).$promise.then(
            	    //success
            	    function( value ){
            	    	$scope.users = UsersFactory.list();
            	    	$scope.onEdit=undefined;
                });
        };

		// callback for ng-click 'createUser':
        $scope.createNewUser = function () {
        	UsersFactory.create($scope.newUser).$promise.then(
            	    //success
            	    function( value ){
            	    	$scope.newUser.user = '';
            	    	$scope.users = UsersFactory.list();
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
app.controller('UserDetailController', ['$scope', '$routeParams', 'UsersFactory', '$location',
    function ($scope, $routeParams, UsersFactory, $location) {

        // callback for ng-click 'updateUser':
        $scope.updateUser = function () {
        	UsersFactory.update($scope.user);
            $location.path('/user-list');
        };

        // callback for ng-click 'cancel':
        $scope.cancel = function () {
            $location.path('/user-list');
        };

        $scope.user = UsersFactory.get({id: $routeParams.id});
    }]);




