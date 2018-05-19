(function(){
	'use strict';
	angular.module('MyService')
	.service('NewsSvervice',NewsService);
	NewsService.$inject=['$http','$filter', '$q'];
	function NewsService(){
		var vm={};
		vm.getNews=getNews;
		vm.getNewsDetailById=getNewsDetailById;
		vm.get
		return vm;

	}
})();