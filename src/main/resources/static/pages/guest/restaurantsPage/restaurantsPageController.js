/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('RestaurantsPageController', RestaurantsPageController);

  RestaurantsPageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'notifyService', 'guestService', 'authenticationService'];

  function RestaurantsPageController($scope, $state, $location, sessionService, notifyService,  guestService, authenticationService) {

    $scope.userInfo = sessionService.getUserInfo();

    $scope.restaurantsPage = []; 
    $scope.currentSort = "name";
    $scope.range = function(n) {
        var x = new Array(Math.round(n));
        for(let i = 0; i < x.length; i++) {
          x[i] = i+1;
        }
        return x;
    };
    $scope.changeSort = function(sortBy) {
      $scope.currentSort = sortBy;
    };
    notifyService.showInfo("Loading restaurant data...Please wait...");
    guestService.getRestaurantsPageInfo($scope.userInfo.userId).then(function (response) {
        $scope.restaurantsPage = response.data; 
    }, function (error) {

    });

    $scope.logoff = function() {
      notifyService.showInfo("Logging off...");
      authenticationService.logoff(); 
      $state.go('login'); 
    
    }

    $scope.reserve = function(id) {
      $state.go('reserve1/:restaurantId', {restaurantId: id}); 
    };
   
  }
})();

 
    