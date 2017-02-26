angular.module('foodbook').controller('waiterProfileController', function($scope, $http, $state, $location, authenticationService) {
	
	
	
	$state.transitionTo('waiter.home');
	
	$scope.home = function() {
		$(".nav").find(".active").removeClass("active");
		$('#li1').addClass('active');
        $state.transitionTo('waiter.home');
    };

    $scope.seeWorkSchedule = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li2').addClass('active');
        $state.transitionTo('waiter.workSchedule');
    };
    
    $scope.seeRestaurantRegion = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li3').addClass('active');
        $state.transitionTo('waiter.restaurantRegion');
    };
    
    $scope.seeOrders = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li4').addClass('active');
        $state.transitionTo('waiter.orders');
    };
    
   
    $scope.logout = function () {
	    authenticationService.logoff();
	    $state.transitionTo('login');
    };
});