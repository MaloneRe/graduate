(function () {
    'use strict';
    var app=angular.module('app');
    app.controller('HomeController'
        , ['$rootScope'
        , '$scope'
        , '$location'
        , '$cookies'
        , 'UserService'
    	,function($rootScope
            , $scope
            , $location
            , $cookies
            , UserService){
    		var vm=this;
            vm.login=login;
    		vm.user={'name':'zhanghui'};
            $scope.user={'name':'zhanghui'};
    		console.log(document.body.clientWidth+"  "+document.body.clientHeight);
    		vm.news=[
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
   		    ];
            $scope.news=vm.news;
   		    vm.test=function(str){
   		    	console.log(str);
   		    }
   		   vm.goto = function(page) {
      			console.log("Goto " + page);
    		}
            function login(url){
                 $location.path(url);
            }
    	}]);
})();