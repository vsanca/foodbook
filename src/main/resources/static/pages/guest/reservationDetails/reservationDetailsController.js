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

        /**
         * ACquire terminOd
         * ACquire confirmed (if this guest is not the owner)
         * 
         */
        guestService.getReservationDetails($scope.reservationId).then(function (response) {
            $scope.reservationDetailsPage = response.data;
        }, function (error) {

        });

        $scope.cancelReservation = function (id) {
            // is it possible to cancel it?
            // check reservation time and current time 
            if ($scope.reservationDetailsPage.terminOd - Date.now() < 60 * 30 * 1000) {
                notifyService.showError("Cannot cancel reservation, since it starts in less than 30 minutes.");
                return;
            }

            guestService.cancelReservation(id).then(function (response) {
                notifyService.showInfo("Reservation canceled!");
            }, function (error) {
                notifyService.showError("Cannot cancel reservation, since it starts in less than 30 minutes.");
            });
        };

        $scope.confirmAttendance = function (id) {
            guestService.confirmAttendance(id).then(function (response) {

            }, function (error) {

            });

        };

        $scope.cancelAttendance = function (id) {
            guestService.cancelAttendance(id).then(function (response) {

            }, function (error) {

            });

        };

    
    }
})();


