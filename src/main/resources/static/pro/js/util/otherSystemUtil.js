var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content"); // X-CSRF-TOKEN

// SMS 발송
var tid;
var SetTime = 0; // 설정시간(기본 : 초)
function sendSMS(mobileNum) {
//	var sendSmsMsg = "[Im Freelancer] 인증번호는 [" + certNum + "] 입니다."
	var sendSmsMsg = "[Im Freelancer] 인증번호는 [certificateNum] 입니다."

	var jsonData = {
		"mobile_num": mobileNum,
		"sendSmsMsg": sendSmsMsg 
	}
		
	$.ajax({
		url:'/linkage/sendSMS',
		dataType: 'text',
		type: 'POST',
		async: false,
		contentType: 'application/json',
		data: JSON.stringify(jsonData),
		beforeSend: function(xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
			xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
	    },
	    success: function(data) {
			if(data == 'created'){
				$('#ViewTimer').html('');
										
				clearInterval( tid ); // 타이머 해제
				SetTime = 180;
				
				$('#time').show();
				tid = setInterval('msg_time()',1000);			
			} else {
				alert('인증번호 발송에 실패하였습니다. 관리자에게 문의하세요.');
			}
		},
		fail: function() {
			alert('인증번호 발송에 실패하였습니다. 관리자에게 문의하세요.');
			console.log('sendSMS error : ', error);
		},
		error:function(xhr,status,error){
			console.log('xhr:'+xhr.responseText);
		}
	});
}	

// SMS 인증처리
function mobileCerNumConfirm(mobileNum) {
	if (loginHdlr.bCertificateCompleteYn == true) {
			alert('이미 인증처리 완료되었습니다.');
	} else {
		if ($('.cnum').val() == '') {
			alert('인증번호를 입력해주세요.');
			return;
		} else {
			var confirmMsg = '';
			var jsonData = {
				mobile_num : mobileNum,
				certificationNumber : $('.cnum').val()
			};
			
			$.ajax({
				url: '/mobile/certNumConfirm',
				dataType: 'text',
				type: 'POST',
				async: false,
				contentType: 'application/json',
				data:JSON.stringify(jsonData),
				beforeSend: function(xhr) {
					xhr.setRequestHeader(CSRF_HEADER,CSRF_TOKEN);
				},
				success: function(data) {
					confirmMsg = data;
				},
				fail: function() {
					confirmMsg = 'fail';
				},
				error:function(xhr,status,error){
					console.log('xhr:'+xhr.responseText);
				}
			});
				
			if (confirmMsg != 'success') {
				alert('인증번호가 일치하지 않습니다.');
				return;
			} else {
				clearInterval( tid ); // 타이머 해제
				alert('인증처리 완료되었습니다.');
				
				// 완료시 인증번호 삭제
				$.ajax({
					url: '/mobile/certNumDelete',
					dataType: 'text',
					type: 'POST',
					async: false,
					contentType: 'application/json',
					data: JSON.stringify(jsonData),
					beforeSend: function(xhr) {
						xhr.setRequestHeader(CSRF_HEADER,CSRF_TOKEN);
					},
					success: function() {},
					fail: function() {},
					error:function(xhr,status,error){
						console.log('xhr:'+xhr.responseText);
					}
				});
				
				$('#time').hide();
				$('.cnum').hide();
				
				loginHdlr.bCertificateCompleteYn = true;
				loginHdlr.mobileCertChk = true;
			}
		}			
	}
}

// 인증번호 타이머 1초씩 카운트
function msg_time() {
	minute = Math.floor(SetTime / 60) + ":" + ( SetTime % 60 );	// 남은 시간 계산
	$('#time').html(minute);
	
	SetTime--; // 1초씩 감소
	
	if(SetTime < 0) {	     // 타이어 종료시	
		clearInterval(tid);		
		$('#time').hide();
		setTimeout(function(){alert( "인증번호 재발송하시기 바랍니다." )}, 100);
	}
}

// 우편번호 검색
function fnZipCode() {
    new daum.Postcode({
        oncomplete: function (data) {
            $('#post_num ').val(data.zonecode);
            $('#addr_1').val(data.roadAddress);
            $('#addr_2').focus();
        }
    }).open({popupName: 'postcodePopup'});
}