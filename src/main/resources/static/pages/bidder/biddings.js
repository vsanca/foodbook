angular.module('foodbook').controller('bidderBiddingsController', function($scope, $http, $state, sessionService, notifyService, bidderService) {
	
	$scope.status = ["OPEN", "CLOSED", "EXPIRED"];
	$scope.currentStatus = $scope.status[0];
	$scope.groceriesList = [];
	
	$scope.selectedGroceries = {};
	
	
	$http.get('/groceries/getAll', 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.groceriesList = data;
				
				var today = new Date();
				
				for(i=0; i<data.length; i++) {
					if(data[i].to_date.valueOf() < today.valueOf()) {
						data[i].status = $scope.status[2];
					}
					
					$scope.groceriesList[i].from_date = new Date(data[i].from_date).toISOString().substring(0, 10);
					$scope.groceriesList[i].to_date = new Date(data[i].to_date).toISOString().substring(0, 10);
					
				}
				
	});

	$scope.newBidding = function(groceries) {
		
		bidderService.setSelectedGroceries(groceries);
		
		$(".nav").find(".active").removeClass("active");
    	$('#li2').addClass('active');
		$state.transitionTo('bidder.newBidding');
		
	};
	
	$scope.previewGroceries = function(groceries) {
		
		$scope.selectedGroceries = groceries;
		
		$http.get('/groceries/getItems/'+ $scope.selectedGroceries.id, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.selectedGroceries.items = data;
					
		});
	};
	
});