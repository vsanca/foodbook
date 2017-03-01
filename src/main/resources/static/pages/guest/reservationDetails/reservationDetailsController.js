/**
 * Displays reservation info, used to pre-order food/drinks or to confirm/cancel reservation
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

    'use strict';

    angular.module('foodbook').controller('ReservationDetailsController', ReservationDetailsController);

    ReservationDetailsController.$inject = ['$scope', '$state', 'sessionService', 'guestService', '$stateParams', 'notifyService'];

    function ReservationDetailsController($scope, $state, sessionService, guestService, $stateParams, notifyService) {

        $scope.userInfo = sessionService.getUserInfo();
        $scope.reservationId = $stateParams.reservationId;

        $scope.models = {
            selected: null,
            lists: { "allOrders": [], "guestOrders": [] }
        };

        // all ordered items are stored in this map
        // already ordered items ARE NOT SHOWN IN THE ALL ORDERS LIST
        $scope.mapOrderedItems = {};
        /**
         * ACquire terminOd
         * ACquire confirmed (if this guest is not the owner)
         * 
         */
        guestService.getReservationDetails($scope.reservationId).then(function (response) {
            for (let i = 0; i < response.data.guestOrders.length; i++) {
                $scope.models.lists.guestOrders.push(
                    {
                        menuItemId: response.data.guestOrders[i].menuItemId,
                        label: response.data.guestOrders[i].menuItemName,
                        bePrepared: response.data.guestOrders[i].bePrepared
                    });
                $scope.mapOrderedItems[response.data.guestOrders[i].menuItemId] = response.data.guestOrders[i];
            }

            // prepare lists for UI
            for (let i = 0; i < response.data.allOrders.length; i++) {
                if ($scope.mapOrderedItems.hasOwnProperty(response.data.allOrders[i].menuItemId)) {
                    continue; // ALREADY ORDERED MENU ITEMS ARE NOT SHOWN IN THE ALL ORDERS LIST!!!!
                }
                $scope.models.lists.allOrders.push(
                    {
                        menuItemId: response.data.allOrders[i].menuItemId,
                        label: response.data.allOrders[i].menuItemName,
                        bePrepared: response.data.allOrders[i].bePrepared

                    });
            }

            $scope.reservationDetailsPage = response.data;
        }, function (error) {

        });



        $scope.cancelReservation = function (id) {
            // is it possible to cancel it?
            // check reservation time and current time 
            if (Math.abs($scope.reservationDetailsPage.terminOd - Date.now()) < 60 * 30 * 1000) {
                notifyService.showError("Cannot cancel reservation, since it starts in less than 30 minutes.");
                return;
            }
            guestService.cancelReservation($scope.reservationId).then(function (response) {
                notifyService.showInfo("Reservation canceled!");
                $state.go('guest-reservations');
            }, function (error) {
                notifyService.showError("Failed to cancel reservation, server error");
            });
        };

        $scope.confirmAttendance = function () {
            guestService.confirmAttendance($scope.reservationId).then(function (response) {
                notifyService.showInfo("Attendance confirmed");
                $scope.reservationDetailsPage.confirmed = true;
            }, function (error) {
                notifyService.showError("Failed to update reservation orders!");
            });

        };

        $scope.cancelAttendance = function () {
            guestService.cancelAttendance($scope.reservationId).then(function (response) {
                notifyService.showInfo("Attendance canceled");
                $scope.reservationDetailsPage.confirmed = false;
            }, function (error) {
                notifyService.showError("Failed to update reservation orders!");
            });

        };

        $scope.updateReservationOrders = function () {
            guestService.updateReservationOrders($scope.reservationId, $scope.models.lists.guestOrders).then(function (response) {
                notifyService.showInfo("Updated reservation orders!");
            }, function (error) {
                notifyService.showError("Failed to update reservation orders!");
            });
        };


    }
})();


