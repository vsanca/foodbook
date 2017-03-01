/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('Reserve1PageController', Reserve1PageController);

  Reserve1PageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'guestService', '$stateParams', 'authenticationService'];

  function Reserve1PageController($scope, $state, $location, sessionService, guestService, $stateParams, authenticationService) {

    $scope.userInfo = sessionService.getUserInfo();

    guestService.getReserve1PageInfo($stateParams.restaurantId).then(function (response) {
      $scope.reserve1Page = response.data;
    }, function (error) {

    });

    $scope.logoff = function () {
      alert("logoff called");
      authenticationService.logoff();
      $state.go('login');
    }
    /*
      console.log($stateParams); 
      console.log($stateParams.restaurantId); */
    $scope.reserve = function (id) {
      let reservationObj = sessionService.getReservationInfo();
      reservationObj.duration = $scope.duration;
      reservationObj.name = $scope.reserve1Page.restaurantName; 
      reservationObj.date = $scope.dt; 
      reservationObj.arrival = $scope.picker2.date; 
      reservationObj.restaurantId = parseInt($stateParams.restaurantId); 
      sessionService.setReservationInfo(reservationObj);
      $state.go('reserve2');
    }

/////////////////////////////////////////////
   // INPUT duration
   $scope.duration = 0;

    //////////////////////////////////////////////////////////////////

    $scope.today = function () {
      // model for date input 
      $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function () {
      $scope.dt = null;
    };

    $scope.inlineOptions = {
      customClass: getDayClass,
      minDate: new Date(),
      showWeeks: true
    };

    $scope.dateOptions = {
      dateDisabled: disabled,
      formatYear: 'yy',
      maxDate: new Date(2020, 5, 22),
      minDate: new Date(),
      startingDay: 1
    };

    // Disable weekend selection
    function disabled(data) {
      var date = data.date,
        mode = data.mode;
      return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
    }

    $scope.toggleMin = function () {
      $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
      $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
    };

    $scope.toggleMin();

    $scope.open1 = function () {
      $scope.popup1.opened = true;
    };

    $scope.open2 = function () {
      $scope.popup2.opened = true;
    };

    $scope.setDate = function (year, month, day) {
      $scope.dt = new Date(year, month, day);
    };

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[0];
    $scope.altInputFormats = ['M!/d!/yyyy'];

    $scope.popup1 = {
      opened: false
    };

    $scope.popup2 = {
      opened: false
    };

    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 1);
    $scope.events = [
      {
        date: tomorrow,
        status: 'full'
      },
      {
        date: afterTomorrow,
        status: 'partially'
      }
    ];

    function getDayClass(data) {
      var date = data.date,
        mode = data.mode;
      if (mode === 'day') {
        var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

        for (var i = 0; i < $scope.events.length; i++) {
          var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);

          if (dayToCheck === currentDay) {
            return $scope.events[i].status;
          }
        }
      }

      return '';
    }

 
$scope.picker2 = {
        date: new Date('2015-03-01T12:30:00Z'),
        timepickerOptions: {
            readonlyInput: false,
            showMeridian: false
        }
    };

$scope.openCalendar = function(e, picker) {
        $scope.picker2.open = true;
};



 

  }
})();


