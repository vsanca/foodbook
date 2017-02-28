angular.module('foodbook').controller('chefHomeController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.activePageNumber = 0;
	
	$scope.chef = {};
	
	$http.get('/user/chef/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.chef = data;
				$scope.chef.id = sessionService.getUserInfo().userId;
				
				if(data.password_set === false){
					$("#myModal").modal();
				}
	});
	
	$scope.modifyChef = function() {
		$http.post('/user/chef/update', $scope.chef,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Podaci uspešno modifikovani!');
				})
				.error(function() {
					notifyService.showError('Greška prilikom modifikacije!');
				});
	};
	
	$scope.cancel = function() {
		$http.get('/user/chef/profile/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.chef = data;
		});
	};
	
	$scope.changePassword = function() {
		$http.post('user/chef/changePassword/', $scope.chef,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno izmenjena lozinka.');
				})
				.error(function() {
					notifyService.showError('Greška prilikom izmene lozinke.');
				});
	};
	
	
});