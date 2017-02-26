(function () {
    'use strict';

    angular
    	.module('foodbook')
    	.factory('bidderService', bidderService);

    function bidderService() {
    	
    	var selectedGroceries;
    	
    	return {
    		setSelectedGroceries: setSelectedGroceries,
    		getSelectedGroceries: getSelectedGroceries
    	};

        function setSelectedGroceries(groceries) {
            selectedGroceries = groceries;
        }
        function getSelectedGroceries() {
            return selectedGroceries;
        }

    }

})();