angular.module('foodbook').controller('managerProfileController', function($scope, $http, $state) {
	
	$state.transitionTo('manager.home');
	
	$scope.home = function() {
        $state.transitionTo('manager.home');
    };

    $scope.groceries = function() {
        $state.transitionTo('manager.groceries');
    };
    
    $scope.menu = function() {
        $state.transitionTo('manager.menu');
    };

    $scope.registerWorker = function() {
        $state.transitionTo('manager.registerWorker');
    };
    
    $scope.report = function() {
        $state.transitionTo('manager.report');
    };
    
    $scope.restaurant = function() {
        $state.transitionTo('manager.restaurant');
    };
    
    $scope.setLayout = function() {
        $state.transitionTo('manager.setLayout');
    };
    
    $scope.setTimetable = function() {
        $state.transitionTo('manager.setTimetable');
    };
    
    $scope.workers = function() {
        $state.transitionTo('manager.workers');
    };
    
    $scope.changeTabTo = function(number) {
    	$scope.tabs[number].active = true;
    }
    
});