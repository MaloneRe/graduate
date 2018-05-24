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
        , '$httpProvider'
        /*, 'BehaviorInterceptor'*/
        , '$locationProvider'
        , function($routeProvider
            , $httpProvider
            /*, BehaviorInterceptor*/
            , $locationProvider){
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

        .when('/:newsID/detail', {
            controller: 'DetailController',
            templateUrl: 'module/detail/detail.view.html',
            /*resolve: ,*/
            controllerAs: 'vm'
           
        })

        .otherwise({ redirectTo: '/' });

        $httpProvider.interceptors.push('BehaviorInterceptor');
    }]);
	app.run(['$rootScope'
        , '$location'
        , '$cookies'
        , '$http'
        , function($rootScope
            , $location
            , $cookies
            , $http){
		  // keep user logged in after page refresh
        $rootScope.newsApp = $cookies.getObject('newsApp') || {};
        
        console.log($rootScope.newsApp);
        
        if ($rootScope.newsApp.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.newsApp.currentUser.authdata;
        }
        var reg = "/\\d+/detail";
        var pattern = new RegExp(reg);
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            let path = $location.path()
            let array = ['/login', '/register'];
            console.log('path:'+path+', '+pattern.exec(path));
            let detailurl = pattern.test(path);
            if (detailurl) {
                array.push(path);
                console.log('array:'+array+', '+detailurl);
            }
            var restrictedPage = $.inArray(path, array) === -1 ;
            console.log('restrictedPage:'+restrictedPage);
            var loggedIn = $rootScope.newsApp.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/');
            }
        });


        $rootScope.$on('$locationChangeStart', function(event, newUrl, oldUrl){
            
            console.log('locationStart=>newUrl:'+newUrl+', oldUrl:'+oldUrl);
            
        });
 
        $rootScope.$on('$locationChangeSuccess', function(event, newUrl, oldUrl){
            
            console.log('locationSuccess=>newUrl:'+newUrl+', oldUrl:'+oldUrl);
            
        });


        $rootScope.$on('$routeChangeStart', function(event, newUrl, oldUrl){
            
            console.log('routeStart=>newUrl:'
                +newUrl.__proto__.regexp
                +', oldUrl:'
                +oldUrl);

            console.log('routeStartTime=>:'+new Date().getTime());
            if (oldUrl) {
                if (oldUrl.$$route.originalPath === '/:newsID/detail') {
                    //alert('equal');
                }
            }
            
        });

        $rootScope.$on('$routeChangeSuccess', function(event, current, previous){
            
            console.log('routeSuccess=>current:'
                +current.$$route.regexp
                +', path:'
                +current.$$route.originalPath
                +', previous:'
                +previous);

             console.log('routeSuccessTime=>:'+new Date().getTime());
            if (current) {
                if (current.$$route.originalPath === '/:newsID/detail') {
                    
                }
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