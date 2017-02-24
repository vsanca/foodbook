angular.module('foodbook').controller('chefProfileController', function($scope, $http, $state, $location) {
	
	
	
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
    
    $scope.changeTabTo = function(number) {
    	$scope.tabs[number].active = true;
    }

    /*
    $scope.logout = function () {
        $state.transitionTo('logout');
    };
    */
});