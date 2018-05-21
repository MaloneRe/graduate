(function () {
    'use strict';
    var app=angular.module('app');
    app.controller('HomeController'
        , ['$rootScope'
        , '$scope'
        , '$location'
        , '$cookies'
        , 'NewsFactory'
        , 'UserService'
        , 'AuthenticationService'
    	,function($rootScope
            , $scope
            , $location
            , $cookies
            , NewsFactory
            , UserService
            , AuthenticationService){
    		var vm=this;
            //return vm;
            vm.login = login;
            vm.logout = logout;
            vm.goto = goto;
            vm.test = test;
            $scope.login = login;
            $scope.goto=goto;
            $scope.test=test;
    		$scope.user={'name':'zhanghui'};
            $scope.user={'name':'zhanghui'};
    		console.log(document.body.clientWidth+"  "+document.body.clientHeight);
    	    var recommend=vm.news=$scope.news=[
    			{'title':'recommend','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
    			{'title':'1223','content':'12323','imageurl':'images/user.svg'},
   		    ];
            var domestic=[
                {'title':'domestic','content':'12323','imageurl':'images/user.svg'},
                {'title':'1223','content':'12323','imageurl':'images/user.svg'}
                ];
            var world=[
                {'title':'world','content':'12323','imageurl':'images/user.svg'},
                {'title':'1223','content':'12323','imageurl':'images/user.svg'}
                ];
            var government=[
                {'title':'government','content':'12323','imageurl':'images/user.svg'},
                {'title':'1223','content':'12323','imageurl':'images/user.svg'}
                ];
            var society=[
                {'title':'society','content':'12323','imageurl':'images/user.svg'},
                {'title':'1223','content':'12323','imageurl':'images/user.svg'}
                ];
            var war=[
                {'title':'war','content':'12323','imageurl':'images/user.svg'},
                {'title':'1223','content':'12323','imageurl':'images/user.svg'}
                ];
                
            var air=[
                {'title':'air','content':'12323','imageurl':'images/user.svg'},
                {'title':'1223','content':'12323','imageurl':'images/user.svg'}
                ];
            var UAV=[
                {'title':'UAV','content':'12323','imageurl':'images/user.svg'},
                {'title':'1223','content':'12323','imageurl':'images/user.svg'}
                ];

   		    function test(str){
   		    	console.log(str);
   		    }
            $scope.currentNavItem='recommend';
   		   function goto(page) {
                let news ; 
                switch(page){
                    case 'recommend':
                        news = recommend; 
                        //console.log("recommend");
                        break;
                    case 'domestic': 
                        news = domestic;
                        //console.log("recommend");
                        break;
                    case 'world': 
                        news = world;
                        //console.log("recommend");
                        break;
                    case 'government': 
                        news = government;
                        //console.log("recommend");
                        break;
                    case 'society': 
                        news = society;
                        //console.log("recommend");
                        break;
                    case 'war': 
                        news = war;
                        //console.log("recommend");
                        break;
                    case 'air': 
                        news = air;
                        //console.log("recommend");
                        break;
                    case 'UAV': 
                        news = UAV;
                        //console.log("recommend");
                        break;
                    default:  
                        news = other;
                        //console.log("recommend");
                        break;
                }
                $scope.news = news;
      			console.log("Goto " + page);
    		};
            function login(url){
                 $location.path(url);
            };

            function logout(commend){
               AuthenticationService.Logout(function(response){
                    if (response.success) {
                       AuthenticationService.ClearCredentials();
                        $location.path('/');
                    } else {
                       console.log(response);
                        AuthenticationService.ClearCredentials();
                    }
               });
            };
    	}]);
})();