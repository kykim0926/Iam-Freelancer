$(function() {
	//메뉴 이동 클릭 이벤트
//  	$('.navi li').not('li.topFirstMenu').on("click", function(){
	$('header .isFrame').on("click", function(){

		var subPage = $(this).attr('folder');
		var detailPage = $(this).attr('page');

		$('#contentBody').load('/pro/moveMenu/' + subPage + '/' + detailPage, '', function(){
			// header의 클래스 변경.
			$('#mainTopMenu').find('header').attr('class', 'sub');
		});
	});
});