angular.module('foodbook').controller('managerAddBidderController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.bidder = {};
	
	$scope.createBidder = function() {
		
		$http.post('user/bidder/create', $scope.bidder,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno dodat ponuđač.');
					$scope.bidder = {};
				})
				.error(function() {
					notifyService.showError('Greška prilikom dodavanja ponuđača.');
				});
		
	};
	
	$scope.cancel = function() {
		$scope.bidder = {};
	};
	
});