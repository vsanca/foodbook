angular.module('foodbook').controller('bidderHomeController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.bidder = {};
	
	$http.get('/user/bidder/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.bidder = data;
				$scope.bidder.id = sessionService.getUserInfo().userId;
				
				if(data.password_set === false)
					$("#myModal").modal();
				
	});
	
	$scope.modifyBidder = function() {
		
		$http.post('user/bidder/update', $scope.bidder,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno ažuriran profil.');
				})
				.error(function() {
					notifyService.showError('Greška prilikom ažuriranja profila.');
				});
		
	};
	
	$scope.cancel = function() {
		$http.get('/user/bidder/profile/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.bidder = data;
					
		});
	};
	
	$scope.changePassword = function () {
		$http.post('user/bidder/changePassword/', $scope.bidder,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno izmenjena lozinka.');
				})
				.error(function() {
					notifyService.showError('Greška prilikom izmene lozinke.');
				});
	};
	
});