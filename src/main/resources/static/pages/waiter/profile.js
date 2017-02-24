angular.module('foodbook').controller('waiterProfileController', function($scope, $http, $state, $location) {
	
	
	
	$state.transitionTo('waiter.home');
	
	$scope.home = function() {
        $state.transitionTo('waiter.home');
    };

    $scope.seeWorkSchedule = function() {
        $state.transitionTo('waiter.workSchedule');
    };
    
    $scope.seeRestaurantRegion = function() {
        $state.transitionTo('waiter.restaurantRegion');
    };
    
    $scope.seeOrders = function() {
        $state.transitionTo('waiter.orders');
    };
    
    $scope.changeTabTo = function(number) {
    	$scope.tabs[number].active = true;
    }

    /*
    $scope.logout = function () {
        $state.transitionTo('logout');
    };
    */
});