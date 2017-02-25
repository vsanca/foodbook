angular.module('foodbook').controller('managerProfileController', function($scope, $http, $state, authenticationService) {
	
	$state.transitionTo('manager.home');
	
	$scope.home = function() {
		$(".nav").find(".active").removeClass("active");
		$('#li1').addClass('active');
        $state.transitionTo('manager.home');
    };

    $scope.groceries = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li8').addClass('active');
        $state.transitionTo('manager.groceries');
    };
    
    $scope.menu = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li7').addClass('active');
        $state.transitionTo('manager.menu');
    };

    $scope.registerWorker = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li4').addClass('active');
        $state.transitionTo('manager.registerWorker');
    };
    
    $scope.report = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li10').addClass('active');
        $state.transitionTo('manager.report');
    };
    
    $scope.restaurant = function() {
    	$(".nav").find(".active").removeClass("active");
    	$('#li2').addClass('active');
        $state.transitionTo('manager.restaurant');
    };
    
    $scope.setLayout = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li5').addClass('active');
        $state.transitionTo('manager.setLayout');
    };
    
    $scope.setTimetable = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li6').addClass('active');
        $state.transitionTo('manager.setTimetable');
    };
    
    $scope.workers = function() {
    	$(".nav").find(".active").removeClass("active");
		$('#li3').addClass('active');
        $state.transitionTo('manager.workers');
    };
    
    $scope.addBidder = function() {
    	$(".nav").find(".active").removeClass("active");
    	$('#li9').addClass('active');
        $state.transitionTo('manager.registerBidder');
    };
    
    $scope.changeTabTo = function(number) {
    	$scope.tabs[number].active = true;
    }
    
    $scope.logout = function () {
    	authenticationService.logoff();
        $state.transitionTo('login');
    };
    
});