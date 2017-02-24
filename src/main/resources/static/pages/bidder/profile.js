angular.module('foodbook').controller('bidderProfileController', function($scope, $http, $state, authenticationService) {
	
	$state.transitionTo('bidder.home');
	
	$scope.home = function() {
        $state.transitionTo('bidder.home');
    };

    $scope.overview = function() {
        $state.transitionTo('bidder.overview');
    };
    
    $scope.biddings = function() {
        $state.transitionTo('bidder.biddings');
    };

    $scope.newBidding = function() {
        $state.transitionTo('bidder.newBidding');
    };
	
    $scope.logout = function () {
    	authenticationService.logoff();
        $state.transitionTo('login');
    };
    
});