(function () {
    'use strict';

    angular
    	.module('foodbook')
    	.factory('bidderService', bidderService);

    function bidderService() {
    	
    	var selectedGroceries;
    	var selectedBidding;
    	
    	return {
    		setSelectedGroceries: setSelectedGroceries,
    		getSelectedGroceries: getSelectedGroceries,
    		setSelectedBidding: setSelectedBidding,
    		getSelectedBidding: getSelectedBidding
    	};

        function setSelectedGroceries(groceries) {
            selectedGroceries = groceries;
        }
        function getSelectedGroceries() {
            return selectedGroceries;
        }
        
        function setSelectedBidding(bidding) {
        	selectedBidding = bidding;
        }
        
        function getSelectedBidding() {
        	return selectedBidding;
        }

    }

})();