var memberHdlr = {
	memberIdExistChk : function(loginId) { // 로그인 아이디 중복 체크
		var bExist = false;
		
		var jsonData = {
			"login_id" : loginId
		}
		
		$.ajax({
			type:'POST',
			contentType:"application/json",
			url:'/member/existChk',
			data:JSON.stringify(jsonData),
			beforeSend : function(xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
				xhr.setRequestHeader(CSRF_HEADER,  CSRF_TOKEN);
            },
			dataType: 'text',
			async: false,
			cache :false,
			success: function(data){
				bExist = data;
			},
			error:function(xhr,status,error){
				console.log('xhr:'+xhr.responseText);
			}
		});
		
		return bExist;
	}	
}