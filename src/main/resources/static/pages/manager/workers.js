angular.module('foodbook').controller('managerWorkersController', function($scope, $http, $state, sessionService) {
	
	$scope.restaurant = {};
	$scope.workers = {};
	
	var waiters = [];
	var bartenders = [];
	var chefs = [];
	
	$scope.order = "name";
	
	$scope.setShift = function () {
		$state.go('manager.setTimetable');
	};
	
	//GET RESTAURANT
	$http.get('/restaurants/manager/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.restaurant = data;
				
				//GET WAITERS
				$http.get('/waiter/forRestaurant/'+$scope.restaurant.id, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						.success(function (data) {
							waiters = data;	
							
							for(i=0; i<waiters.length; i++) {
								waiters[i].waiter = true;
								waiters[i].bartender = false;
								waiters[i].chef = false;
							}
							
							//GET BARTENDERS
							$http.get('/bartender/forRestaurant/'+$scope.restaurant.id, 
									{ headers: { 'Authorization': sessionService.getAuthToken() } })
									.success(function (data) {
										bartenders = data;	
										
										for(i=0; i<bartenders.length; i++) {
											bartenders[i].waiter = false;
											bartenders[i].bartender = true;
											bartenders[i].chef = false;
										}
										
										//GET CHEFS
										$http.get('/chef/forRestaurant/'+$scope.restaurant.id, 
												{ headers: { 'Authorization': sessionService.getAuthToken() } })
												.success(function (data) {
													chefs = data;	
													
													for(i=0; i<chefs.length; i++) {
														chefs[i].waiter = false;
														chefs[i].bartender = false;
														chefs[i].chef = true;
													}
													
													//GET ALL WORKERS
													$scope.workers = chefs.concat(bartenders).concat(waiters);
										})
							})
				})
	});
	
});