angular.module('foodbook').controller('chefProfileController', function($scope, $http, $state, $location) {
	
	$scope.activePageNumber = 0;
	
	$state.transitionTo('chef.home');
	
	$scope.home = function() {
        $state.transitionTo('chef.home');
    };

    $scope.seeWorkSchedule = function() {
        $state.transitionTo('chef.workSchedule');
    };
    
    $scope.seeOrders = function() {
        $state.transitionTo('chef.orders');
    };

    /*
    $scope.logout = function () {
        $state.transitionTo('logout');
    };
    */
});