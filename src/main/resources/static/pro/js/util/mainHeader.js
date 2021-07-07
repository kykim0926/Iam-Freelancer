$(function() {
	//메뉴 이동 클릭 이벤트
	$('header .isFrame').on("click", function() {

		var subPage = $(this).attr('folder');
		var detailPage = $(this).attr('page');

		var jsonData = { data: '2', dddd: '3fefe' };
		$('#contentBody').load('/pro/moveMenu/' + subPage + '/' + detailPage, 'param=' + encodeURI(JSON.stringify(jsonData)), function() {
			// header의 클래스 변경.
			$('#mainTopMenu').find('header').attr('class', 'sub');
			initEmail();
		});
	});

	/*
	    top menu의 sub page로 로드가 되서 email 셀렉트 박스 이벤트 다시 bind.
	 */
	function initEmail() {
		$('.select-items').on('click', function() {
			if ($("#search_email_domain option:selected").val() == '') {
				$('#email_domain').attr('disabled', false); // 직접입력란 활성화
				$('#email_domain').css('background', '#f5f5f8');
			} else {
				$('#email_domain').attr('disabled', true); // 직접입력란 비활성화
				$('#email_domain').css('background', '#a9a9c1');
				$('#email_domain').val(''); // 직접입력란 비활성화
			}
		});
	}
});