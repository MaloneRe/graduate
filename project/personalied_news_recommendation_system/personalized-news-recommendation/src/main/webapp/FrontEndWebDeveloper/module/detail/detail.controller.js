(function () {
    'use strict';

    /*angular
        .module('app')
        .controller('DetailController', DetailController);

    DetailController.$inject =
     ['$location'
     , 'AuthenticationService'
     , 'FlashService'];
    function DetailController($location
    	, AuthenticationService
    	, FlashService) {
        var vm = this;
    }*/
    var app = angular.module('app');
    app.controller('DetailController', ['$rootScope'
    	, '$scope'
    	, '$location'
    	, 'AuthenticationService'
    	, 'FlashService',function($rootScope
    		, $scope
    		, $location
    		, AuthenticationService
    		, FlashService){
    		 var vm = this;
    		 vm.goBack=goBack;
    		 vm.test=test;
    		 vm.comment=[
    		 {'id':12, 'content':'twt', 'time':'2018-09-9'}
    		 , {'id':13, 'content':'45', 'time':'2018-08-12'}
    		 , {'id':23, 'content':'fbbffbf', 'time':'2018-08-12'}
    		 , {'id':33, 'content':'erfege', 'time':'2018-08-12'}
    		 , {'id':45, 'content':'3434vcc', 'time':'2018-08-12'}
    		 ]
    		 return vm;
    		 $scope.content="";
    		 function goBack(url){
    		 	$location.path(url);
    		 }
    		 function test(comment){
    		 	console.log(comment);	
    		 }

    	}]);

})();