/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('RestaurantsPageController', RestaurantsPageController);

  RestaurantsPageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'guestService', 'authenticationService'];

  function RestaurantsPageController($scope, $state, $location, sessionService, guestService, authenticationService) {
    alert("restaurants blabla");
    $scope.userInfo = sessionService.getUserInfo();

    $scope.restaurantsPage = []; 

    guestService.getRestaurantsPageInfo($scope.userInfo.userId).then(function (response) {
        $scope.restaurantPage = response.data; 
    }, function (error) {

    });

    $scope.logoff = function() {
      alert("logoff called");
      authenticationService.logoff(); 
      $state.go('login'); 
    
    }

    $scope.reserve = function(id) {
      guestService.getReserve1PageInfo().then(function (response) {
        $scope.reserve1Page = response.data; 
      }, function (error) {

      }); 
      $state.go('reserve1'); 
    }
   
  }
})();

 
    