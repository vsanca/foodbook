angular.module('foodbook').controller('waiterProfileController', function($scope, $http, $state, $location) {
	
	$scope.activePageNumber = 0;
	
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

    /*
    $scope.logout = function () {
        $state.transitionTo('logout');
    };
    */
});