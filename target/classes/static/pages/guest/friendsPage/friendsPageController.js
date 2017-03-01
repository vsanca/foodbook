/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('FriendsPageController', FriendsPageController);

  FriendsPageController.$inject = ['$scope', '$state', '$route', '$location', 'sessionService', 'guestService', 'authenticationService'];

  function FriendsPageController($scope, $state, $route, $location, sessionService, guestService, authenticationService) {
    $scope.userInfo = sessionService.getUserInfo();

    $scope.friendsPage = [];

    guestService.getFriendsPageInfo($scope.userInfo.userId).then(function (response) {
      $scope.friendsPage = response.data;
    }, function (error) {

    });

    guestService.addPeople($scope.userInfo.userId).then(function (response) {
      $scope.addPeople = response.data;
    }, function (error) {

    });

    $scope.dodaj = function (id) {
      guestService.sendFriendshipRequest(id).then(function (response) {

        alert("uspesno kreiran zahtev za prijateljstvo");
        $route.reload();
      }, function (error) {

      });

    };

    guestService.acceptPeople($scope.userInfo.userId).then(function (response) {
      $scope.acceptPeople = response.data;
    }, function (error) {

    });

    $scope.prihvati = function (id) {
      guestService.acceptFriendshipRequest(id).then(function (response) {

        alert("uspesno prihvacen zahtev za prijateljstvo");
        $route.reload();

      }, function (error) {

      });
    }

    $scope.odbaci = function (id) {
      guestService.rejectFriendshipRequest(id).then(function (response) {

        alert("uspesno odbijen zahtev za prijateljstvo");
        $route.reload();

      }, function (error) {

      });
    }




    $scope.logoff = function () {
      alert("logoff called");
      authenticationService.logoff();
      $state.go('login');

    };

  }
})();


