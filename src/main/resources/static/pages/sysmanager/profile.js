angular.module('foodbook').controller('sysmanagerProfileController', function($scope, $http, $state, $location) {
	
	$scope.activePageNumber = 0;
	
	$state.transitionTo('sysmanager.home');
	
	$scope.home = function() {
        $state.transitionTo('sysmanager.home');
    };

    $scope.addBidder = function() {
        $state.transitionTo('sysmanager.addBidder');
    };
    
    $scope.addRestaurant = function() {
        $state.transitionTo('sysmanager.addRestaurant');
    };

    $scope.addRestaurantManager = function() {
        $state.transitionTo('sysmanager.addRestaurantManager');
    };

    $scope.addSysmanager = function() {
        $state.transitionTo('sysmanager.addSystemManager');
    };

    /*
    $scope.logout = function () {
        $state.transitionTo('logout');
    };
    */
});