/**
 * Created by Viktor on 12/21/2016.
 */

angular.module('foodbookApp.RegisterGuestController', [])
    .controller('RegisterGuestController', function ($scope, $location, RegisterGuestFactory) {
        function init() {

        }

        init();

        $scope.registerGuest = function (guest) {
            RegisterGuestFactory.newGuest(guest)
                .success(function (data) {
                    alert('proverite mejl!');
                })
                .error(function (data) {
                    alert('greska!');
                })
        }
    });