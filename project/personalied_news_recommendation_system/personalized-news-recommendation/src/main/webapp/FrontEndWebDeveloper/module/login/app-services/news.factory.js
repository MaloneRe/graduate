(function () {
	'use strict';
	angular
        .module('MyService')
        .factory('NewsFactory', NewsFactory);

    NewsFactory.$inject = ['$rootScope', 'NewsSvervice'];
    function NewsFactory($rootScope, newsSvervice){
    	var factory = {};
    	factory.NewList = NewList;
    	factory.NewsDetail = NewsDetail;
    	factory.TodayNews = TodayNews;

    	factory.Recommender = Recommender;

    	factory.Comment = Comment;
    	factory.CommentList = CommentList;

    	factory.CollectionAndShare = CollectionAndShare;

    	return factory;

    	function NewList(category, callback){
    		let response;
    		newsSvervice.getNews(category)
    		.then(function(data){
    			if (data !== null ) {
                   	console.log(data);
                    response = { success: true };
                } else {
                    response = { success: false
                       	, message: 'error'
                    };
                }
                callback(response);
    		}, function(data){
    			console.log(data);
                    response = { success: false
                    , message: 'error'
                };
                callback(response);
    		});
    	};
    	function NewsDetail(newsid, callback){
    		let response;
    		newsSvervice.getNewsDetailById(newsid)
    		.then(function(data){
    			if (data != null) {
    				console.log(data);
    			}else{

    			}
    			callback(response);
    		}, function(data){
    			console.log(data);
    			callback(response);
    		});
    	};

    	function TodayNews(callback){
    		let response;
    		newsSvervice.getTopNews()
    		.then(function(data){
    			if (data != null) {
    				console.log(data);
    			}else{

    			}
    			callback(response);
    		}, function(data){
    			console.log(data);
    			callback(response);
    		});
    	};

    	function Recommender(callback){
    		let response;
    		newsSvervice.getRecommend()
    		.then(function(data){
    			if (data != null) {
    				console.log(data);
    			}else{

    			}
    			callback(response);
    		}, function(data){
    			console.log(data);
    			callback(response);
    		});
    	};
    	function Comment(comment, callback){
    		let response;
    		newsSvervice.createComment(comment.userID
    			, comment.mewsID
    			, comment.content)
    		.then(function(data){
    			if (data != null) {
    				console.log(data);
    			}else{

    			}
    			callback(response);
    		}, function(data){
    			console.log(data);
    			callback(response);
    		});
    	};
    	function CommentList(newsID, callback){
    		let response;
    		newsSvervice.getComment(newsID)
    		.then(function(data){
    			if (data != null) {
    				console.log(data);
    			}else{

    			}
    			callback(response);
    		}, function(data){
    			console.log(data);
    			callback(response);
    		});
    	};
    	function CollectionAndShare(behavior, callback){
    		let response;
    		newsSvervice.behavior(behavior.userID
    			, behavior.newsID
    			, behavior.behavior)
    		.then(function(data){
    			if (data != null) {
    				console.log(data);
    			}else{

    			}
    			callback(response);
    		}, function(data){
    			console.log(data);
    			callback(response);
    		});
    	};

    };
})();