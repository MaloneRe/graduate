(function(){
	'use strict';
        /*angular.module('app')加载已有app模块*/
	/*var app=angular.module('app',['ngRoute','ngCookies','ngMaterial']);*/
    //var app=angular.module('app');
	//app.$inject=['ngRoute','ngCookies','ngMaterial'];
   
   var app=angular.module('app'
    /*s, ['ngRoute'
    , 'ngCookies'
    , 'ngMaterial'
    , 'MyService'
    ]*/);
    app.config(['$routeProvider'
        , '$locationProvider'
        , function($routeProvider, $locationProvider){
        $routeProvider
        .when('/', {
            controller: 'HomeController',
            templateUrl:'module/home/home.view.html',
            controllerAs: 'vm'
        })

        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'module/login/login/login.view.html',
            controllerAs: 'vm'
        })

        .when('/register', {
            controller: 'RegisterController',
            templateUrl: 'module/login/register/register.view.html',
            controllerAs: 'vm'
        })

        .when('/detail', {
            controller: 'DetailController',
            templateUrl: 'module/detail/detail.view.html',
            /*resolve: ,*/
            controllerAs: 'vm'
           
        })

        .otherwise({ redirectTo: '/' });
    }]);
	app.run(['$rootScope', '$location', '$cookies', '$http',function($rootScope, $location, $cookies, $http){
		// keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('newsApp') || {};
        
        console.log($rootScope.globals);
        
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login'
                , '/register'
                , '/detail']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/');
            }
        });
	}]);
    /*app.directive('UserService', ['UserService', function (UserService) {

    }]);*/
   /* app.controller('HomeController'
        , ['$rootScope'
        , '$scope'
        , 'UserService'
        , function($rootScope,$scope,UserService){
            var vm=this;
            vm.user={'name':'zhanghui'};
            $scope.user={'name':'zhanghui'};
            console.log(vm);

        }]);*/

})();