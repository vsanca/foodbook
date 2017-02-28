/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('Reserve2PageController', Reserve2PageController);

  Reserve2PageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'guestService', '$stateParams', 'authenticationService'];

  function Reserve2PageController($scope, $state, $location, sessionService, guestService, $stateParams, authenticationService) {

    $scope.userInfo = sessionService.getUserInfo();

    $scope.reserve2Page = sessionService.getReservationInfo();
    initializeTables();
    $scope.date = $scope.reserve2Page.date.toString();

    $scope.logoff = function () {
      alert("logoff called");
      authenticationService.logoff();
      $state.go('login');
    }

    // maps each table ID to table
    // table is added after double click if not present inside this map
    // 
    $scope.selectedTables = {};

    $scope.reserve = function (id) {
      let reservationObj = sessionService.getReservationInfo();
      reservationObj.tables = [];
      for(let tableId in $scope.selectable) {
        tables.push(tableId);
      }
      sessionService.setReservationInfo(reservationObj);
      $state.go('reserve3');
    }
   


    function initializeTables() {

      var canvas = new fabric.CanvasEx('restaurant_canvas');
      $scope.canvas = canvas;

      $scope.colors = ['white', 'red', 'blue', 'green', 'yellow'];

      $scope.areas = [];

      $scope.tables = [];

      $scope.area = {};

      $scope.table = {};


      canvas.backgroundColor = '#d0d5dd';

      canvas.renderAll();

      $scope.redrawTables = function () {
        canvas.clear();

        for (var i in $scope.tables) {

          var table = $scope.tables[i];

          var t = JSON.parse(table.fabricTable);
    
          var fabricTable;

          fabricTable = new fabric.Rect(t);

          fabricTable.selectable = false;
          canvas.add(fabricTable);
           //Render the text after the block (so that it is in front of the block)
          canvas.add(new fabric.Text(table.name, { 
              left: fabricTable.left + fabricTable.width/2, //Take the block's position
              top: fabricTable.top - fabricTable.height/2, 
              fill: fabricTable.fill,
              selectable: false
          }));
          table.fabricTable = fabricTable;

        }

        canvas.renderAll();
      };

       canvas.on('mouse:dblclick', function (options) {
        console.log("click triggered");
        for (var i in $scope.tables) {
            
        	var t = $scope.tables[i];
            
        	if (t.fabricTable == options.target) {
                $scope.$apply(function () {
                    
                    $scope.table = t;
                    if($scope.selectedTables.hasOwnProperty($scope.table.id)) {
                      t.fabricTable.fill = "green";
                      options.target.fill = "green";
                      // if present...remove it from list 
                      delete $scope.selectedTables[$scope.table.id];
                    } else {
                      t.fabricTable.fill = "red";
                      options.target.fill = "red";
                      $scope.selectedTables[$scope.table.id] = $scope.table;
                    }
                    console.log("Selected table:",  $scope.table);
                });
            }
        }
    });

      // fetch tables from backend...initialize render 
      guestService.getReserve2PageInfo($scope.reserve2Page.restaurantId).then(function (response) {
        $scope.tables = response.data;
        $scope.redrawTables();

      }, function (error) {

      });
    }

  }
})();


