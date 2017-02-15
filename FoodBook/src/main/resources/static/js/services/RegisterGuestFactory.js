/**
 * Created by Viktor on 12/21/2016.
 */

angular.module('foodbookApp.RegisterGuestFactory',[])
    .factory('RegisterGuestFactory', function ($http) {
        var factory = {};
        
        factory.newGuest = function (guest) {
            return $http.post('/registerGuest', {"name": guest.name, "surname": guest.surname, "password": guest.password, "email": guest.email});
        }

        return factory;
    });