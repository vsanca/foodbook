/**
 * Created by Viktor on 12/21/2016.
 */

angular.module('foodbookApp.LoginController', [])
    .controller('LoginController', function ($localStorage, $scope, $location, LoginFactory) {
        function init() {

        }

        init();

        $scope.login = function (user) {
            LoginFactory.getUser(user)
                .success(function (data) {
                    if(data){
                        $localStorage.logged = data;
                        if($localStorage.logged.type == 'GUEST') {
                            $location.path('/guestProfile');
                        }
                    }
                })
                .error(function (data) {
                    alert('!');
                })
        }
    });