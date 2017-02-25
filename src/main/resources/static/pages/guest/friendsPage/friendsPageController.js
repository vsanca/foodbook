/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('FriendsPageController', FriendsPageController);

  FriendsPageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'guestService', 'authenticationService'];

  function FriendsPageController($scope, $state, $location, sessionService, guestService, authenticationService) {
    $scope.userInfo = sessionService.getUserInfo();

    $scope.friendsPage = []; 

    guestService.getFriendsPageInfo($scope.userInfo.userId).then(function (response) {
        $scope.friendsPage = response.data; 
    }, function (error) {

    });

    $scope.logoff = function() {
      alert("logoff called");
      authenticationService.logoff(); 
      $state.go('login'); 
    
    }

   
  }
})();

 
    