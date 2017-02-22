foodbook.controller('sysmanagerProfileController', function($scope, $http, $state) {
	$scope.home = function() {
        $state.transitionTo('system_manager.home');
    };

    $scope.addRestaurant = function() {
        $state.transitionTo('system_manager.addRestaurant');
    };

    $scope.addRestaurantManager = function() {
        $state.transitionTo('system_manager.addRestaurantManager');
    };

    $scope.addSystemManager = function() {
        $state.transitionTo('system_manager.addSystemManager');
    };

    $scope.addProvider = function() {
        $state.transitionTo('system_manager.addProvider');
    };

    $scope.logout = function () {
        $state.transitionTo('logout');
    };
});