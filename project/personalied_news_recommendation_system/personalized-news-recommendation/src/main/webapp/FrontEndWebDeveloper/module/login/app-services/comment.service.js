(function(){
	'use strict';
	angular.module('MyService')
	.service('CommentService',CommentService);
	CommentService.$inject=['$http','$filter', '$q'];
	function CommentService(){
		var vm={};
		vm.getNews=getNews;
		vm.getNewsDetailById=getNewsDetailById;
		vm.get
		return vm;
		
	}
})();