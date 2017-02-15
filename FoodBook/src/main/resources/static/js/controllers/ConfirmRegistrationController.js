/**
 * Created by Viktor on 12/21/2016.
 */

angular.module('foodbookApp.ConfirmRegistrationController', [])
    .controller('ConfirmRegistrationController', function ($scope, $location, ConfirmRegistrationFactory) {
        function init() {

        }

        init();

        $scope.confirm = function (user) {
            ConfirmRegistrationFactory.confirmGuest(user)
                .success(function (data) {
                    if(data){
                        $location.path('/');
                    }
                })
                .error(function (data) {
                    alert('Wrong username or password!');
                });
        }
    });