/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('FriendsPageController', FriendsPageController);

  FriendsPageController.$inject = ['$scope', '$location', 'sessionService', 'guestService'];

  function FriendsPageController($scope, $location, sessionService, guestService) {
    $scope.userInfo = sessionService.getUserInfo();

    $scope.friendsPage = []; 

    guestService.getFriendsPageInfo($scope.userInfo.userId).then(function (response) {
        $scope.friendsPage = response.data; 
    }, function (error) {

    });
   
  }
})();

 
    