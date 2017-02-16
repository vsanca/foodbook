/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbookApp.guest', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
      $routeProvider.when('/guest/profile-page', {
        templateUrl: 'guest/profilePage/profilePage.html'
      });
    }]).controller('ProfilePageController', ProfilePageController)
    .service('GuestService', GuestService);

  ProfilePageController.$inject = ['$scope', '$http', 'GuestService'];

  function ProfilePageController($scope, $http, GuestService) {
    alert("Poyyy");

    $scope.init = function () {

      GuestService.getProfilePageInfo(1).then(function (response) {
        $scope.profilePageDTO = response.data;
        $scope.name = response.data.name;
        $scope.surname = response.data.surname;
        $scope.address = response.data.address;
        $scope.numberOfVisits = response.data.numberOfVisits;
      }, function () {
      });
    };


  }
})();