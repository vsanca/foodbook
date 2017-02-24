angular.module('foodbook').controller('bartenderProfileController', function($scope, $http, $state, $location) {
	
	
	
	$state.transitionTo('bartender.home');
	
	$scope.home = function() {
        $state.transitionTo('bartender.home');
    };

    $scope.seeWorkSchedule = function() {
        $state.transitionTo('bartender.workSchedule');
    };

    $scope.seeOrders = function() {
        $state.transitionTo('bartender.orders');
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