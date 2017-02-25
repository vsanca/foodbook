angular.module('foodbook').controller('bidderProfileController', function($scope, $http, $state, authenticationService) {
	
	$state.transitionTo('bidder.home');
	
	$scope.home = function() {
		$(".nav").find(".active").removeClass("active");
		$('#li1').addClass('active');
        $state.transitionTo('bidder.home');
    };

    $scope.overview = function() {
    	$(".nav").find(".active").removeClass("active");
    	$('#li3').addClass('active');
        $state.transitionTo('bidder.overview');
    };
    
    $scope.biddings = function() {
    	$(".nav").find(".active").removeClass("active");
    	$('#li4').addClass('active');
        $state.transitionTo('bidder.biddings');
    };

    $scope.newBidding = function() {
    	$(".nav").find(".active").removeClass("active");
    	$('#li2').addClass('active');
        $state.transitionTo('bidder.newBidding');
    };
	
    $scope.logout = function () {
    	authenticationService.logoff();
        $state.transitionTo('login');
    };
    
});