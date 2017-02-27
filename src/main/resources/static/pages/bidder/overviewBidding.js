angular.module('foodbook').controller('bidderOverviewBiddingController', function($scope, $http, $state, sessionService, notifyService, bidderService) {
	
	$scope.biddingStatus = ["ACTIVE", "ACCEPTED", "REJECTED", "EXPIRED"];
	$scope.groceriesStatus = ["OPEN", "CLOSED", "EXPIRED", "TBA"];
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
		
		$http.get('/groceries/getItems/'+ $scope.selectedBidding.groceries.id, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.selectedGroceryItems = data;
					
					$http.get('/bidding/getBidItemsByBidding/'+ $scope.selectedBidding.id, 
							{ headers: { 'Authorization': sessionService.getAuthToken() } })
							.success(function (data) {
								
								$scope.selectedBiddingItems = data;
								
								$('#previewBiddingModal').modal();
					});
		});
	};
	
	$scope.modifyBidding = function(bidding) {
		
		$('#previewBiddingModal').modal('hide');
		
		$('#previewBiddingModal').on('hidden.bs.modal', function () {
			
			var groceries = bidding.groceries;
			groceries.items = $scope.selectedGroceryItems;
			
			var biddingWithItems = bidding;
			biddingWithItems.items = $scope.selectedBiddingItems;
			
			bidderService.setSelectedGroceries(groceries);
			bidderService.setSelectedBidding(biddingWithItems);
			$state.transitionTo('bidder.newBidding');
		});
	};
	
});