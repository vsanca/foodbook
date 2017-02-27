angular.module('foodbook').controller('bartenderProfileController', function($scope, $http, $state, $location, authenticationService) {
	
	
	
	$state.transitionTo('bartender.home');
	
	$scope.home = function() {
		$(".nav").find(".active").removeClass("active");
		$('#li1').addClass('active');
        $state.transitionTo('bartender.home');
    };

    $scope.seeWorkSchedule = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li2').addClass('active');
        $state.transitionTo('bartender.workSchedule');
    };

    $scope.seeOrders = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li3').addClass('active');
        $state.transitionTo('bartender.orders');
    };
    
    $scope.changeTabTo = function(number) {
    	$scope.tabs[number].active = true;
    }

    $scope.logout = function () {
	    authenticationService.logoff();
	    $state.transitionTo('login');
    };
});