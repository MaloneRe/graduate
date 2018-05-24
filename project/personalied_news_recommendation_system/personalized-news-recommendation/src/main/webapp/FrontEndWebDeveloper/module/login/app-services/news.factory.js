(function () {
	'use strict';
	angular
        .module('MyService')
        .factory('NewsFactory', NewsFactory);

    NewsFactory.$inject = ['$rootScope', 'NewsSvervice'];
    function NewsFactory($rootScope, newsSvervice){
    	var factory = {};
    	factory.NewsList = NewsList;
    	factory.NewsDetail = NewsDetail;
    	factory.TodayNews = TodayNews;

    	factory.Recommender = Recommender;

    	factory.Comment = Comment;
    	factory.CommentList = CommentList;

    	factory.CollectionAndShare = CollectionAndShare;

    	return factory;

    	function NewsList(category, callback){
    		let response;
    		newsSvervice.getNews(category)
    		.then(function(data){
    			if (data !== null ) {
                   	console.log(data);
                   	
                    response = { 
                    	success: true,
                    	data:data
                    };
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
    				response = {
    					success:true,
    					content:data.content
    				};
    			}else{
    				response = {
    					success:false,
    					content:"出错了"
    				};
    			}
    			callback(response);
    		}, function(data){
    			console.log(data);
    			response = {
    					success:false,
    					content:"出错了"
    				};
    			callback(response);
    		});
    	};

    	function TodayNews(callback){
    		let response;
    		newsSvervice.getTopNews()
    		.then(function(data){
    			if (data != null && data.success) {
    				console.log(data);
    				response = {
    					success:true,
    					content:data.content
    				};
    			}else{
    				response = {
    					success:false,
    					message:'error'
    				};
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
    				let array =[];
    				for (var i = 0; i < data.length; i++) {
    					let comment ={
    						commentID:data[i].id,
    						userName: data[i].username,
    						userID:data[i].uid,
    						newsID:data[i].nid,
    						content:data[i].content,
    						time:data[i].time
    					};
    					array.push(comment);
    				};
    				response = {
    					success:true,
    					content:array
    				};
    			}else{
    				response ={
    					success:false,
    					message:"error"
    				};
    			}
    			callback(response);
    		}, function(data){
    			    response ={
    					success:false,
    					message:data
    				};
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
    				response = data;
    			}else{

    			}
    			callback(response);
    		}, function(data){
    			console.log(data);
    			response = data;
    			callback(response);
    		});
    	};

    };
})();