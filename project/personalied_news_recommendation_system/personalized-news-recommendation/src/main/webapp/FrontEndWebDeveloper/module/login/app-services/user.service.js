(function () {
    'use strict';

    angular
        .module('MyService')
        .factory('UserService', UserService);

    UserService.$inject = ['$http','$filter', '$q'];
    function UserService($http, $filter, $q) {
        var service = {};

      
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        service.Exit = Exit;
        return service;

       
       

        function GetByUsername(username) {
            var deferred = $q.defer();     
            $http.get('/api/users/' + username).then(function(response){
                deferred.resolve(response.data);
            }, function(response){
                deferred.reject(response);
            });
            return deferred.promise;
        }

        function Create(user) {
            var deferred = $q.defer();     
            $http.post('/api/users', user).then(function(response){
                deferred.resolve(response.data);
            }, function(response){
                deferred.reject(response);
            });
            return deferred.promise;
        }

        function Exit(){
            let deferred = $q.defer(); 
            $http.delete('/api/users',{}).then(function(response){
                deferred.resolve(response.data);
            }, function(response){
                deferred.reject(response);
            });
            return deferred.promise;
        }

        /*
            以下作废
        */
         function GetById(id) {
            var deferred = $q.defer();     
            $http.get('/api/users/' + id).then(function(response){
                deferred.resolve(response.data);
            }, function(response){
                deferred.reject(response);
            });
            return deferred.promise;
        }

        function Update(user) {
            var deferred = $q.defer();     
            $http.put('/api/users/' + user.id, user).then(function(response){
                deferred.resolve(response.data);
            }, function(response){
                deferred.reject(response);
            });
            return deferred.promise;
        }

        function Delete(id) {
            var deferred = $q.defer();
            $http.delete('/api/users/' + id).then(function(response){
                deferred.resolve(response.data);
            }, function(response){
                deferred.reject(response);
            });
            return deferred.promise;
        }

        // private 
    }

})();
