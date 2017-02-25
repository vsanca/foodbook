angular.module('foodbook').controller('managerGroceriesController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.listItems = [];
	$scope.biddings = [];
	$scope.groceryItemTypes = [];
	$scope.groceryItemQuantities = [];
	$scope.groceriesList = [];
	$scope.selectedGroceryItems = [];
	$scope.selectedBiddingItems = [];
	
	$scope.status = ["OPEN", "CLOSED", "EXPIRED", "TBA"];
	$scope.currentStatus = "TBA";
	
	$scope.newGroceryItem = {};
	$scope.newGroceryItemType = {};
	$scope.newGroceryItemQuantity = {};
	$scope.selectedList = {};
	$scope.selectedBidding = {};
	$scope.restaurant = {};
	$scope.groceries = {
		from_date: new Date(),
		to_date: new Date(),
		status: $scope.status[3]
	};
	
	
	$http.get('/restaurants/manager/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.restaurant = data;
				$scope.groceries.restaurantId = $scope.restaurant.id;
				
				$http.get('/groceries/getAllForRestaurant/'+ $scope.groceries.restaurantId, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						.success(function (data) {
							$scope.groceriesList = data;
							
							var today = new Date();
							
							for(i=0; i<data.length; i++) {
								if(data[i].to_date.valueOf() < today.valueOf()) {
									$scope.groceriesList[i].status = $scope.status[2];
									
									$http.post('/groceries/expire', $data[i].id,
											{ headers: { 'Authorization': sessionService.getAuthToken() } })
											.success(function (data) {
												$scope.selectedList = data;
											})
								}
								
								$scope.groceriesList[i].from_date = new Date(data[i].from_date).toISOString().substring(0,10);
								$scope.groceriesList[i].to_date = new Date(data[i].to_date).toISOString().substring(0,10);
							}
							
							for(j=0; j< $scope.groceriesList.length; j++) {
								
								$http.get('/bidding/getBiddingForGroceries/'+ $scope.groceriesList[i].id, 
										{ headers: { 'Authorization': sessionService.getAuthToken() } })
										.success(function (data) {
											$scope.biddings.push(data);									
								})
								
							}
				});
	});
	
	
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
	
	
	$scope.addNewListItem = function() {
		$scope.newGroceryItem = {};
		$scope.newGroceryItemType = {};
		$scope.newGroceryItemQuantity = {};
		
		$('#newListItem').modal();
	};
	
	
	$scope.cancel = function() {
		
		$scope.groceries = {
			from_date: new Date(),
			to_date: new Date(),
			status: $scope.status[4]
		};
		
		$scope.listItems = [];
		$scope.newGroceryItem = {};
		
		$('#newListItem').modal('hide');
	};
	
	
	$scope.addListItem = function() {
		
		$scope.listItems.push($scope.newGroceryItem);
		
		$('#newListItem').modal('hide');
	};
	
	
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
	
	
	$scope.cancelNewGroceries = function() {
		
		$scope.groceries = {
			from_date: new Date(),
			to_date: new Date(),
			status: $scope.status[4]
		};
		
		$scope.listItems = [];
		$scope.newGroceryItem = {};
		
	};
	
	
	$scope.addGroceries = function() {
		
		if($scope.listItems.length == 0) {
			notifyService.showError('Lista sa namirnicama nema stavki!');
		} else {
			
			$scope.groceries.status = "TBA";
			
			$http.post('/groceries/new', $scope.groceries,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						
						var item;
						
						for(i=0; i<$scope.listItems.length; i++) {
							
							item = {
								"name": $scope.listItems[i].name,
								"quantity": $scope.listItems[i].quantity,
								"groceriesItemType": $scope.listItems[i].type.id,
								"groceriesItemQty": $scope.listItems[i].quantityType.id,
								"groceriesId": data.id
							}
							
							$http.post('/groceries/item/new', item,
									{ headers: { 'Authorization': sessionService.getAuthToken() } })
									.success(function (data) {
									});
							
						}
						
						data.from_date = new Date(data.from_date).toISOString().substring(0,10);
						data.to_date = new Date(data.to_date).toISOString().substring(0,10);
						
						$scope.groceriesList.push(data);
						
						$scope.groceries = {
								from_date: new Date(),
								to_date: new Date(),
								status: $scope.status[3]
						};
						
						$scope.listItems = [];
						$scope.newGroceryItem = {};
						
					});
		}
	};
	
	
	$scope.deleteItem = function(i) {
		
		$scope.listItems.splice(i,1);
	};
	
	
	$scope.previewList = function(list) {
		
		$scope.selectedList = list;
		
		$http.get('/groceries/getItems/'+ list.id, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.selectedList.items = data;
					
					$('#previewOneList').modal();
		});
	};
	
	
	$scope.activateList = function(list) {
		
		var today = new Date();
		var to_date = new Date(list.to_date);
		
		if(to_date.valueOf() <= today.valueOf()) {
			notifyService.showError('Istekao je definisani rok za nabavku namirnica.');
			
			$http.post('/groceries/expire', list.id,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						
						data.from_date = new Date($scope.selectedList.from_date).toISOString().substring(0,10);
						data.to_date = new Date($scope.selectedList.to_date).toISOString().substring(0,10);
						
						$scope.selectedList = data;
						
						var index = $scope.groceriesList.indexOf(list);
						
						if(index != -1) {
							$scope.groceriesList.splice(index, 1);
						}
						
						$scope.groceriesList.push(data);
					})
			
		} else {
			
			$http.post('/groceries/open', list.id,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						
						data.from_date = new Date($scope.selectedList.from_date).toISOString().substring(0,10);
						data.to_date = new Date($scope.selectedList.to_date).toISOString().substring(0,10);
						
						$scope.selectedList = data;
						
						var index = $scope.groceriesList.indexOf(list);
						
						if(index != -1) {
							$scope.groceriesList.splice(index, 1);
						}
						
						$scope.groceriesList.push(data);
					})
			
		}
		
	};
	
	
	$scope.previewBidding = function(b) {
		
		$scope.selectedBidding = b;
		$scope.selectedBidding.timestamp = new Date(b.timestamp).toISOString().substring(0,10);
		
		$http.get('/groceries/getItems/'+ $scope.selectedBidding.groceries.id, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.selectedGroceryItems = data;	
					
					$http.get('/bidding/getBidItemsByBidding/'+ $scope.selectedBidding.id, 
							{ headers: { 'Authorization': sessionService.getAuthToken() } })
							.success(function (data) {
								
								$scope.selectedBiddingItems	= data;
								$('#biddingForm').modal();
					});
		});
		
	};
	
	
	$scope.getBiddingItemPrice = function(id) {
		
		for(i=0; i< $scope.selectedBiddingItems.length; i++) {
			if($scope.selectedBiddingItems[i].groceryItem.id == id) {
				return $scope.selectedBiddingItems[i].price;
			}
		}
		
		return "";
	};
	
	
	$scope.getBiddingItemName = function(id) {
		
		for(i=0; i< $scope.selectedBiddingItems.length; i++) {
			if($scope.selectedBiddingItems[i].groceryItem.id == id) {
				return $scope.selectedBiddingItems[i].name;
			}
		}
		
		return "";
	};
	
	
	$scope.getBiddingItemQuantity = function(id) {
		
		for(i=0; i< $scope.selectedBiddingItems.length; i++) {
			if($scope.selectedBiddingItems[i].groceryItem.id == id) {
				return $scope.selectedBiddingItems[i].quantity;
			}
		}
		
		return "";
	};
	
	
	$scope.getBiddingItemQuantityType = function (id) {
		
		for(i=0; i< $scope.selectedBiddingItems.length; i++) {
			if($scope.selectedBiddingItems[i].groceryItem.id == id) {
				return $scope.selectedBiddingItems[i].groceryItemQty.name;
			}
		}
		
		return "";
	};
	
	
	$scope.closeGroceries = function(list, bid) {
		
		for(i=0; i< $scope.groceriesList.length; i++) {
			
			if($scope.groceriesList[i].id == list.id) {
				
				$http.post('/groceries/close', list.id,
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						.success(function (data) {
							notifyService.showSuccess('Zatvoreno.');
						})
						
			}
			
		}
		
		for(i=0; i< $scope.biddings.length; i++) {
			
			if($scope.biddings[i].length>0) {
				if($scope.biddings[i][0].groceries.id == list.id) {
					
					for(j=0; j< $scope.biddings[i].length; j++) {
						
						if($scope.biddings[i][j].id == bid.id) {
							
							$http.post('/bidding/accept', bid.id,
									{ headers: { 'Authorization': sessionService.getAuthToken() } })
									.success(function (data) {
										notifyService.showSuccess('Prihvaćena ponuda.');
									})
							
						} else {
							
							$http.post('/bidding/reject', bid.id,
									{ headers: { 'Authorization': sessionService.getAuthToken() } })
									.success(function (data) {
										notifyService.showSuccess('Odbijena ponuda.');
									})
							
						}
					}
					
				}
			}
		}
	};
	
});