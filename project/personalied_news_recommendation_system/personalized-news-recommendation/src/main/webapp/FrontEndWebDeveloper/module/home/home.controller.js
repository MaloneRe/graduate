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
            vm.detail = detail;
            vm.newsList = newsList ;

    		$scope.user={'name':'zhanghui'};
            $scope.user={'name':'zhanghui'};
    		console.log('clientWidth:'+document.body.clientWidth
                +", clientHeight:"+document.body.clientHeight);
    	    var /*$scope.news=*/recommend=vm.news=[
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
            var other = [{}];
   		    function test(str){
   		    	console.log(str);
   		    };
           /* $scope.currentNavItem='recommend';*/
           vm.currentNavItem = 'recommender';
           /* vm.recommender = function(){
                let currentUser = $rootScope.newsApp.currentUser;
                if (!currentUser) {
                     NewsFactory.Recommender(function(response){

                    });
                }
                console.log('test recommender');
            };
            vm.recommender();*/
           vm.newsList(vm.currentNavItem);
            vm.todaynews = function(){
                NewsFactory.TodayNews(function(response){
                    if (response.success) {
                        $scope.todaynews = response.content; 
                        //console.log('home todaynews:'+response);
                    }else{
                        $scope.todaynews = [];
                    }
                });
            }
            vm.todaynews();

            function detail(newsID){
                if (newsID) {
                     $location.path('/'+newsID+'/detail');
                }                
            };
   		   function goto(page) {
                let news ; 
                let category;
                switch(page){
                    case 'recommender':
                        news = recommend;
                        category = 'recommender'; 
                        //console.log("recommend");
                        break;
                    case 'domestic': 
                        category = 'domestic';
                        news = domestic;
                        //console.log("recommend");
                        break;
                    case 'world': 
                        category = 'world';
                        news = world;
                        //console.log("recommend");
                        break;
                    /*case 'government': 
                        category = 'government';
                        news = government;
                        //console.log("recommend");
                        break;*/
                    case 'society': 
                        category = 'society';
                        news = society;
                        //console.log("recommend");
                        break;
                    case 'war': 
                        category = 'war';
                        news = war;
                        //console.log("recommend");
                        break;
                    case 'air': 
                        category = 'air';
                        news = air;
                        //console.log("recommend");
                        break;
                    case 'UAV': 
                        category = 'UAV';
                        news = UAV;
                        //console.log("recommend");
                        break;
                    default:  
                        category = 'other';
                        news = other;
                        //console.log("recommend");
                       
                        break;
                }
                vm.news = {};
                newsList(category);
      			console.log("Goto " + page);
                console.log(vm.news);
                
    		};

            function newsList(category){
                console.log('newsList:'+category);
                let news;
                if (category !== 'recommender') {
                    
                    NewsFactory.NewsList(category
                        , function(response){
                            if (response.success) {
                                let data= response.data||{};
                                let array = [];
                                for (var i = 0; i < data.length; i++) {
                                    let news = {
                                        newsID:data[i].id,
                                        imageurl:data[i].imageUrl,
                                        title:data[i].title,
                                        docurl:data[i].url,
                                        time:data[i].time
                                    };
                                    array.push(news); 
                                }
                               /* console.log('newsList data:'
                                +JSON.parse(JSON.stringify(news)));
                               */
                                vm.news = news = array; 
                                //console.log(news);
                            }
                        
                    });
                    
                }else{
                    let currentUser = $rootScope.newsApp.currentUser;
                    if (currentUser) {
                        NewsFactory.Recommender(function(response){

                        });
                    }
                       
                }
                return news;                 
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