(function(){
	'use strict';
	angular.module('MyService')
	.service('NewsSvervice',NewsService);
	NewsService.$inject=['$http','$filter', '$q'];
	function NewsService(){
		var vm={};
		vm.getNews=getNews;
		vm.getNewsDetailById=getNewsDetailById;
		vm.getTopNews = getTopNews;
		vm.getRecommend = getRecommend;
		/*
		评论系列
		*/
		vm.createComment = createComment;
		vm.getComment = getComment;

		vm.behavior = behavior;
		return vm;
		function getNews(category){
			var deferred = $q.defer();     
            $http.get('/api/news/' + category)
            .then(function(response){
                deferred.resolve(response.data);
            }, function(response){
                deferred.reject(response);
            });
            return deferred.promise;
		};

		function getNewsDetailById(nid){
			var deferred = $q.defer();     
            $http.get('/api/news/' + nid+'/content')
            .then(function(response){
                deferred.resolve(response.data);
            }, function(response){
                deferred.reject(response);
            });
            return deferred.promise;
		};

		function getTopNews(){
			var deferred = $q.defer();     
            $http.get('/api/todaynews')
            .then(function(response){
                deferred.resolve(response.data);
            }, function(response){
                deferred.reject(response);
            });
            return deferred.promise;
		};

		function getRecommend(){
			let deferred = $q.defer();
			$http.get('/api/recommender')
			.then(function(response){
				deferred.resolve(response.data);
			}, function(response){
				deferred.reject(response);
			});
			return deferred.promise;
		};

		/**
		 评论相关
		*/
		function createComment(userid, newsid, comment){
			let deferred = $q.defer();
			$http.post('/api/'+newsid+'/comment',{
				userid:userid
				, newsid:newsid
				, content:comment 
			})
			.then(function(response){
				deferred.resolve(response.data);
			}, function(response){
				deferred.reject(response);
			});
			return deferred.promise;
		};
		function getComment(newsid){
			let deferred = $q.defer();
			$http.get('/api/'+newsid+'/comment')
			.then(function(response){
				deferred.resolve(response.data);
			}, function(response){
				deferred.reject(response);
			});
			return deferred.promise;
		};
		function behavior(userid, newsid, behavior){
			let deferred = $q.defer();
			$http.post('/api/'+newsid, {
				userid:userid
				, newsid:newsid
				, behavior:behavior 
			})
			.then(function(response){
				deferred.resolve(response.data);
			}, function(response){
				deferred.reject(response);
			});
			return deferred.promise;
		}
	}
})();