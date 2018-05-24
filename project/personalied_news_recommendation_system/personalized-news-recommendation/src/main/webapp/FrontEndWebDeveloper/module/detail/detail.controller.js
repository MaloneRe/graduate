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
        , '$routeParams'
        , '$mdDialog'
        , 'NewsFactory'
    	, 'AuthenticationService'
    	, 'FlashService',function($rootScope
    		, $scope
    		, $location
            , $routeParams
            , $mdDialog
            , NewsFactory
    		, AuthenticationService
    		, FlashService){
            console.log('DetailController newsID:'+$routeParams.newsID);
    		 var vm = this;
             vm.currentUser = $rootScope.newsApp.currentUser;
    		 vm.goBack=goBack;
    		 vm.test=test;
             vm.newsID = $routeParams.newsID;
             vm.submit = submit;
             vm.commentList = commentList;
             vm.detail = detail;
             vm.behavior = behavior;
             vm.detail(vm.newsID);
             vm.commentList(vm.newsID);
    		 vm.comment=[
    		 {'id':12, 'content':'twt', 'time':'2018-09-9'}
    		 , {'id':13, 'content':'45', 'time':'2018-08-12'}
    		 , {'id':23, 'content':'fbbffbf', 'time':'2018-08-12'}
    		 , {'id':33, 'content':'erfege', 'time':'2018-08-12'}
    		 , {'id':45, 'content':'3434vcc', 'time':'2018-08-12'}
    		 ];
             function detail(newsID){
                if (newsID) {
                    NewsFactory.NewsDetail(newsID
                        , function(response){
                            console.log('executor detail');
                            let body = document.getElementById('news_body');
                            body.innerHTML = response.content;
                    });
                }
             };
    		 function commentList(newsID){
                if (newsID) {
                    NewsFactory.CommentList(newsID
                        , function(response){
                            console.log(response);
                            vm.comments = response.content;
                    });
                }
                
             };
             function behavior(event, action){
                
                if (vm.currentUser) {
                    let behavior = {
                        userID:vm.currentUser.userid,
                        newsID:vm.newsID,
                        behavior:action
                    } 
                    NewsFactory.CollectionAndShare(behavior
                        , function(response){
                            console.log('detail behavior:'+response);
                           
                    });
                }else{
                    console.log('not behavior: '+action);
                }
             };
    		 $scope.content="";
    		 function goBack(url){
    		 	$location.path(url);
    		 };
    		 function test(event, comment){
    		 	console.log(comment);	
    		 };

             function submit(event, content){
                console.log('content:'+content);
               
                if (vm.currentUser) {
                    if (content) {
                        let comment = {
                            userID:vm.currentUser.userid,
                            newsID:vm.newsID,
                            content:content
                        };
                        NewsFactory.Comment(comment
                            , function(response){

                        });
                    }
                    
                }else{
                   let confirm = $mdDialog.confirm()
                        .title('提示')
                        .textContent('请登录之后再评论')
                        .ariaLabel('Lucky day')
                        .targetEvent(event)
                        .ok('登录')
                        .cancel('取消');
                    $mdDialog.show(confirm).then(function() {
                        $location.path('/login');
                    }, function() {
                        console.log('cancel');
                    });
                }
             }
    	}]);

})();