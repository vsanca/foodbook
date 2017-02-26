angular.module('foodbook').controller('bidderNewBiddingController', function($scope, $http, $state, sessionService, notifyService, bidderService) {
	
	$scope.selectedGroceries = bidderService.getSelectedGroceries();
	
	$scope.groceryItemTypes = [];
	$scope.groceryItemQuantities = [];
	$scope.status = ["OPEN", "CLOSED", "EXPIRED"];
	$scope.biddingStatus = ["TBA", "ACTIVE", "ACCEPTED", "REJECTED", "EXPIRED"];
	$scope.biddingItems = [];
	
	$scope.newGroceryItemType = {};
	$scope.newGroceryItemQuantity = {};
	$scope.selectedGroceryItem = {};
	
	$scope.biddingItem = {};
	$scope.bidding = {};
	$scope.biddingExists = false;
	$scope.biddingId = {};
	
	
	$http.get('/groceries/type/all', 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.groceryItemTypes = data;								
	});
	
	
	$http.get('/groceries/quantity/all', 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.groceryItemQuantities = data;							
	});
	
	
	$scope.addItemType = function() {
		if($scope.newGroceryItemType) {
			
			$http.post('/groceries/type/new', $scope.newGroceryItemType,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						$scope.groceryItemTypes.push(data);
					});
			
		}
	};
	
	
	$scope.addItemQuantity = function() {
		if($scope.newGroceryItemQuantity) {
			
			$http.post('/groceries/quantity/new', $scope.newGroceryItemQuantity,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						$scope.groceryItemQuantities.push(data);
					});
			
		}
	};
	
	
	$http.get('/bidding/getBiddingByGroceriesAndBidder/' + $scope.selectedGroceries.id +'/'+ sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				
				$scope.biddingExists = true;
				
				$scope.biddingId = data.id;
				
				$scope.bidding.status = data.status;
				$scope.bidding.timestamp = new Date(data.timestamp);
				$scope.bidding.price = data.price;
				$scope.bidding.bidderId = data.bidder.id;
				$scope.bidding.groceriesId = data.groceries.id;
				$scope.bidding.currency = data.currency;
				
				$http.get('/bidding/getBidItemsByBidding/' + data.id, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						.success(function (data) {
							
							$scope.biddingItems = data;
							
							for(i=0; i< $scope.biddingItems.length; i++) {
								$scope.biddingItems[i].groceryItemId = $scope.biddingItems[i].groceryItem.id;
								$scope.biddingItems[i].groceryItemQtyId = $scope.biddingItems[i].groceryItemQty.id;
							}
							
						});
				
	});
	
	
	helperFunction = function() {
		for(i=0; i< $scope.biddingItems.length; i++) {
			if($scope.biddingItems[i].groceryItemId == $scope.selectedGroceryItem.id) {
				return $scope.biddingItems[i];
			}
		}
		
		return null;
	};
	
	
	$scope.setNewBiddingItem = function(ind) {
		$scope.selectedGroceryItem = $scope.selectedGroceries.items[ind];
		$scope.biddingItem = helperFunction();
		
		$('#biddingItemDetails').modal();
		
	};
	
	
	$scope.calculatePrice = function() {
		total = 0;
		
		for(i=0; i< $scope.biddingItems.length; i++) {
			total = total + parseFloat($scope.biddingItems[i].price);
		}
		
		return total;
	};
	
	
	$scope.addBiddingItem = function() {
		
		if(helperFunction() == null) {
			
			$scope.biddingItem.groceryItemId = $scope.selectedGroceryItem.id;
			$scope.biddingItems.push($scope.biddingItem);

		} else {
			
			for(i=0; i< $scope.biddingItems.length; i++) {
				if($scope.biddingItems[i].groceryItemId == $scope.selectedGroceryItem.id) {
					$scope.biddingItems[i].price = $scope.biddingItem.price;
					$scope.biddingItems[i].name = $scope.biddingItem.name;
					$scope.biddingItems[i].quantity = $scope.biddingItem.quantity;
					$scope.biddingItems[i].groceryItemQty = $scope.biddingItem.groceryItemQty;
				}
			}
		}
		
		$scope.bidding.price = $scope.calculatePrice();
	};
	
	
	$scope.addNewGroceries = function() {
		
		$scope.bidding.status = $scope.biddingStatus[1];
		$scope.bidding.timestamp = new Date();
		$scope.bidding.price = $scope.calculatePrice();
		$scope.bidding.bidderId = sessionService.getUserInfo().userId;
		$scope.bidding.groceriesId = $scope.selectedGroceries.id;
		
		
		if($scope.biddingItems.length == 0) {
			notifyService.showError('Ne postoji stavka ponude, molimo dodajte stavku kako bi se ponuda mogla kreirati!');
		} else if (! $scope.biddingExists) {	// new
			
			console.log('NEW');
			
			$http.post('/bidding/new', $scope.bidding,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						
						var item;
						
						for(i=0; i< $scope.biddingItems.length; i++) {
							
							item = {
								"name": $scope.biddingItems[i].name,
								"qty": $scope.biddingItems[i].quantity,
								"price": $scope.biddingItems[i].price,
								"groceryItemId": $scope.biddingItems[i].groceryItemId,
								"groceryItemQtyId": $scope.biddingItems[i].groceryItemQty.id,
								"biddingId": data.id
							}
							
							$http.post('/bidding/biddingItem/new', item,
									{ headers: { 'Authorization': sessionService.getAuthToken() } })
									.success(function (data) {
										
										notifyService.showSuccess('Uspešno dodata stavka.');
										
									});
							
						}
						
					});
			
		} else { // update
			
			console.log('UPDATE');
			
			$http.post('/bidding/update/' + $scope.biddingId, $scope.bidding,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						
						var item;
						
						for(i=0; i< $scope.biddingItems.length; i++) {
							
							item = {
								"name": $scope.biddingItems[i].name,
								"qty": $scope.biddingItems[i].quantity,
								"price": $scope.biddingItems[i].price,
								"groceryItemId": $scope.biddingItems[i].groceryItemId,
								"groceryItemQtyId": $scope.biddingItems[i].groceryItemQty.id,
								"biddingId": data.id
							}
							
							$http.post('/bidding/biddingItem/update/' + $scope.biddingItems[i].id, item,
									{ headers: { 'Authorization': sessionService.getAuthToken() } })
									.success(function (data) {
										
										notifyService.showSuccess('Uspešno ažurirana stavka.');
										
									});
							
						}
						
					});
			
		}
	}
		
});