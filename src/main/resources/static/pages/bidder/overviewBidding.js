angular.module('foodbook').controller('bidderOverviewBiddingController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.biddingStatus = ["TBA", "ACTIVE", "ACCEPTED", "REJECTED", "EXPIRED"];
	$scope.currentStatus = $scope.biddingStatus[0];
	$scope.biddings = [];
	$scope.selectedBiddingItems = [];
	$scope.selectedGroceryItems = [];
	
	$scope.selectedBidding = {};
	$scope.selectedBiddingItem = {};
	
	
	$http.get('/bidding/getByBidder/'+ sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				
				$scope.biddings = data;
				
	});
	
	$scope.previewBidding = function(bidding) {
		
		$scope.selectedBidding = bidding;
		$scope.selectedBidding.timestamp = new Date(bidding.timestamp);
		
		$http.get('/groceries/getItems/'+ $scope.selectedBiding.groceries.id, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.selectedGroceryItems = data;
					
					$http.get('/bidding/getBidItemsByBidding/'+ $scope.selectedBiding.id, 
							{ headers: { 'Authorization': sessionService.getAuthToken() } })
							.success(function (data) {
								
								$scope.selectedBiddingItems = data;
								
					});
		});
	};
	
	$scope.showDetails = function(id) {
		
		for(i=0; i< $scope.selectedBiddingItems.length; i++) {
			if($scope.selectedBiddingItems[i].groceryItem.id == id) {
				$scope.selectedBiddingItem = $scope.selectedBiddingItems[i];
			}
		}
		
		$('#biddingDetails').modal();
	}
	
	$scope.getBiddingItemPrice = function(id) {
		
		for(i=0; i< $scope.selectedBiddingItems.length; i++) {
			if($scope.selectedBiddingItems[i].groceryItem.id == id) {
				return $scope.selectedBiddingItems[i].price;
			}
		}
		
		return "";
	};
	
});