(function () {
    'use strict';
   	angular.module('MyService',[]);
    angular.module('app',
    ['ngRoute'
    , 'ngCookies'
    , 'ngMaterial'
    , 'MyService'
    ]);
    
    
})();