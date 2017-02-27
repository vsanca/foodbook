angular.module('foodbook').controller('chefProfileController', function($scope, $http, $state, $location, authenticationService) {
	
	
	
	$state.transitionTo('chef.home');
	
	$scope.home = function() {
		$(".nav").find(".active").removeClass("active");
		$('#li1').addClass('active');
        $state.transitionTo('chef.home');
    };

    $scope.seeWorkSchedule = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li2').addClass('active');
        $state.transitionTo('chef.workSchedule');
    };
    
    $scope.seeOrders = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li3').addClass('active');
        $state.transitionTo('chef.orders');
    };
    
    $scope.changeTabTo = function(number) {
    	$scope.tabs[number].active = true;
    }

    $scope.logout = function () {
	    authenticationService.logoff();
	    $state.transitionTo('login');
    };
});