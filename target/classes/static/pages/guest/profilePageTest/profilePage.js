/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('ProfilePageController', ProfilePageController);

  ProfilePageController.$inject = ['$scope', '$http', 'guestService', 'sessionService'];

  function ProfilePageController($scope, $http, guestService , sessionService) {
    alert("Poyyy");
    $scope.userInfo = sessionService.getUserInfo();

      guestService.getProfileDetails($scope.userInfo.userId).then(function (response) {
        $scope.profilePageDTO = response.data;
        $scope.name = response.data.name;
        $scope.surname = response.data.surname;
        $scope.address = response.data.address;
        $scope.numberOfVisits = response.data.numberOfVisits;
      }, function (err) {

      });
    


  }
})();