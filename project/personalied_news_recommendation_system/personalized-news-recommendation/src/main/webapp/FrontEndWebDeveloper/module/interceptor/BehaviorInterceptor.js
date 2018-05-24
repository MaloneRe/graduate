(function () {
	'use strict';
	angular
        .module('MyService')
        .factory('BehaviorInterceptor', BehaviorInterceptor);

    BehaviorInterceptor.$inject = ['$q'];
    function BehaviorInterceptor($q){
    	let myInterceptor = {};
    	myInterceptor.request =  request;
    	myInterceptor.requestError = requestError;
    	myInterceptor.response = response;
    	myInterceptor.responseError = responseError;
    	return myInterceptor; 
    	function request(config){
    		console.log('interceptor request: '+config);
    		
    		return config;
    	};
    	function requestError(rejection){
    		console.log('interceptor requestError:'+rejection);

    		return rejection;
    	};
    	function response(response){
    		console.log('interceptor response: '+response);

    		return response;
    	};
    	function responseError(rejection){
    		console.log('interceptor responseError: '+rejection);

    		return rejection;
    	};
    };
})();