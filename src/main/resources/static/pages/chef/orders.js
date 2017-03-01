angular.module('foodbook').controller('chefOrdersController', function($scope, $http, $state,$location, sessionService ,authenticationService) {
	
	$scope.activePageNumber = 2;
	
	$scope.orders= [];
	

	
	/*$http.get('/user/chef/allUnfinishedOders/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.orders= [];
				
	});*/
	
	$http.get('/user/chef/allUnfinishedOdersTEST/'+sessionService.getUserInfo().userId, 
	{ headers: { 'Authorization': sessionService.getAuthToken() } })
	.success(function (data) {
		$scope.orders= data;
		alert("USPELO");
		
	});
	
});