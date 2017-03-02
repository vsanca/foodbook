angular.module('foodbook').controller('waiterOrdersController', function($scope, $http, $state,$location, sessionService , notifyService,authenticationService) {
	
	$scope.activePageNumber = 2;
	
	$scope.chef = {};
	
	$http.get('/user/waiter/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.chef = data;
				$scope.chef.id = sessionService.getUserInfo().userId;
				
				$http.get('/user/waiter/allUnfinishedOdersTEST/'+sessionService.getUserInfo().userId, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						
									.success(function (data) {
							
										console.log(data);
										$scope.myOrdersDTO = data;
							
							
							
						});
				
				
	});
	

	
	
	
	
	$scope.acceptOrder = function(o){
		
		$http.get('/user/waiter/acceptedOrder/'+ o.myId +'/'+ sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.myOrdersDTO = data;
					
					
					notifyService.showSuccess('Porudžbina je dostavljena!');
				})
				.error(function() {
					notifyService.showError('Porudžbina nije uspešno dostavljena!');
				});
	}
	
	
	
});