(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService'
    , '$location'
    , '$rootScope'
    , 'FlashService'
    , 'AuthenticationService'
    ];
    function RegisterController(UserService
        , $location
        , $rootScope
        , FlashService
        , AuthenticationService) {
        var vm = this;

        vm.register = register;

        /*function register() {
            vm.dataLoading = true;
            UserService.Create(vm.user)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Registration successful', true);
                        $location.path('/login');
                        
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }*/
        function register(user){
             vm.dataLoading = true;
             AuthenticationService.Register(user,function(response){
                if (response.success) {
                    FlashService.Success('Registration successful', true);
                    $location.path('/login');
                        
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
             });
        }
    }

})();
